package com.dudegenuine.repos.network

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dudegenuine.repos.network.Resource.Companion.TIMEOUT_CODE
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException
import kotlin.coroutines.coroutineContext

/**
 * Manual Book created by utifmd on 22/06/21.
 */
abstract class ResourceManager<ResultType, RequestType>{
    private val TAG: String = javaClass.simpleName

    private val result = MutableLiveData<Resource<ResultType>>()

    fun commit() = result as LiveData<Resource<ResultType>>

    @MainThread
    fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) result.postValue(newValue)

        Log.d(TAG, "setValue Resource: ${newValue.status}")
    }

    private suspend fun manageData() { // result: ResultType) {
        Log.d(TAG, "Fetch data from network")
        setValue(Resource.onLoading()) // Dispatch latest value quickly (UX purpose)

        setValue(Resource.onSuccess(
            data = processRequest(fetchDataRemote())
        ))

        Log.d(TAG, "data fetched from network")

        /*saveCallResults(processResponse(apiResponse))
        setValue(Resource.success(loadFromDb()))*/
    }

    suspend fun build(): ResourceManager<ResultType, RequestType> {
        val supervisorJob = SupervisorJob()

        withContext(Dispatchers.Main) {
            result.value = Resource.onLoading()
        }

        CoroutineScope(coroutineContext).launch(supervisorJob) {
            try {
                manageData()
            } catch (exception: Exception) {
                when(exception){
                    is HttpException -> setValue(Resource.onError(null,
                        Resource.onErrorMessage(exception.code())))
                    is SocketTimeoutException -> setValue(Resource.onError(null,
                        Resource.onErrorMessage(TIMEOUT_CODE)))
                    else -> setValue(Resource.onError(null,
                        Resource.onErrorMessage(Int.MAX_VALUE)))
                }
            }

            /*val localData = fetchDataLocal()
            if (shouldFetch(localData)) {

            } else {
                Log.d(TAG, "Return data from local database")
                setValue(Resource.onSuccess(localData))
            }*/
        }

        return this
    }

    @MainThread
    protected abstract fun shouldFetch(result: ResultType?): Boolean

    @MainThread
    protected abstract suspend fun fetchDataRemote(): RequestType

    @WorkerThread
    protected abstract fun processRequest(response: RequestType): ResultType

    @WorkerThread
    protected abstract fun saveData(result: ResultType)

    @MainThread
    protected abstract fun fetchDataLocal(): ResultType
}