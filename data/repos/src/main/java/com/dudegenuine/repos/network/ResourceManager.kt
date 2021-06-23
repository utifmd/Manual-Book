package com.dudegenuine.repos.network

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

/**
 * Manual Book created by utifmd on 22/06/21.
 */
abstract class ResourceManager<ResultType, RequestType>: IResourceManager<ResultType, RequestType> {
    private val TAG: String = javaClass.simpleName
    private var result = Resource<ResultType>(Resource.Status.LOADING) //MutableLiveData<Resource<ResultType>>()

    @MainThread
    override fun setValue(newValue: Resource<ResultType>) {
        Log.d(TAG, "Resource: $newValue")

        if (result != newValue) result = newValue
        // if (result.value != newValue) result.postValue(newValue)
    }

    @MainThread
    protected abstract fun shouldFetch(result: ResultType?): Boolean

    @MainThread
    protected abstract fun fetchDataRemote(): Observable<RequestType>

    @WorkerThread
    protected abstract fun processRequest(response: RequestType): ResultType

    @WorkerThread
    protected abstract fun saveData(result: ResultType)

    @MainThread
    protected abstract fun fetchDataLocal(): ResultType

    private fun manageDatas(){ //result: ResultType) {
        Log.d(TAG, "Fetch data from network")
        // setValue(Resource.onLoading()) // Dispatch latest value quickly (UX purpose)

        fetchDataRemote()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .subscribe(object: Observer<RequestType> {
                override fun onNext(request: RequestType) {
                    Log.d(TAG, "onNext: triggered")

                    setValue(Resource.onSuccess(processRequest(request)))

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
                    Log.d(TAG, "onSubscribe: triggered")
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete: triggered")
                    Log.e(TAG, "Data fetched from network")
                }
            })
    }

    override fun build(): ResourceManager<ResultType, RequestType> {
        result = Resource.onLoading() // result.value = Resource.onLoading()

        try {
            manageDatas()
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

    fun asData(): Resource<ResultType> = result
    // fun asData(): Resource<ResultType> = result.value!!
    // override fun asLiveData(): LiveData<Resource<ResultType>> = result
}