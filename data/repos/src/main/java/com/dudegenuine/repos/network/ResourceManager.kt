package com.dudegenuine.repos.network

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

/**
 * Manual Book created by utifmd on 22/06/21.
 */
abstract class ResourceManager<ResultType, RequestType> { //: IResourceManager<ResultType, RequestType>
    private val TAG: String = javaClass.simpleName
    val result = MutableLiveData<Resource<ResultType>>() //Resource<ResultType>(Resource.Status.LOADING)

    @MainThread
    fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) result.postValue(newValue) // if (result != newValue) result = newValue

        Log.d(TAG, "setValue Resource: ${newValue.status}")
    }

    @MainThread
    protected abstract fun shouldFetch(result: ResultType?): Boolean

    @MainThread
    protected abstract fun fetchDataRemote(): Observable<out RequestType>

    @WorkerThread
    protected abstract fun processRequest(response: RequestType): ResultType

    @WorkerThread
    protected abstract fun saveData(result: ResultType)

    @MainThread
    protected abstract fun fetchDataLocal(): ResultType

    private fun manageData(){ //result: ResultType) {
        Log.d(TAG, "Fetch data from network")
        setValue(Resource.onLoading()) // Dispatch latest value quickly (UX purpose)

        fetchDataRemote()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .subscribe(object: Observer<RequestType> {
                override fun onNext(request: RequestType) {
                    Log.d(TAG, "onNext: triggered ${result.value?.status}")

                    setValue(Resource.onSuccess(data = processRequest(request)))

                    /*runBlocking {
                        saveData(processResponse(request))
                        setValue(Resource.onSuccess(fetchDataLocal()))
                    }*/
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError: ${e.localizedMessage}")
                    setValue(Resource.onError(throwable = e, data = null))
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe: triggered ${result.value?.status}")
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete: Data fetched from network: ${result.value?.status}")
                }
            })
    }

    fun build(): ResourceManager<ResultType, RequestType> {
        // result.value = Resource.onLoading() // result = Resource.onLoading()

        try {
            manageData()
        } catch (e: Exception) {
            Log.e(TAG, "An error happened: $e")
            setValue(Resource.onError(data = null, throwable = e))
        }

        /*val localData = fetchDataLocal()
        if (shouldFetch(localData)) {

        } else {
            Log.d(TAG, "Return data from local database")
            setValue(Resource.onSuccess(localData))
        }*/

        return this
    }
    fun asLiveData() = result as LiveData<Resource<ResultType>>

/*{
        Log.d(TAG, "asLiveData: ${result.value?.status}")
        Log.d(TAG, "asLiveData: ${result.value?.data}")
        return result
    }*/
    // val mLiveData: LiveData<Resource<ResultType>> get() = result
}