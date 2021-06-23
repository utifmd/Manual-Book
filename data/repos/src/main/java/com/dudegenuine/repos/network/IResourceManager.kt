package com.dudegenuine.repos.network

import androidx.lifecycle.LiveData
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 22/06/21.
 */
interface IResourceManager<ResultType, RequestType> {
    fun setValue(newValue: Resource<ResultType>)

    // fun shouldFetch(result: ResultType?): Boolean
    // fun fetchDataRemote(): RequestType

    // fun dataMapper(request: RequestType): ResultType
    // suspend fun saveData(result: ResultType)

    // suspend fun fetchDataLocal(): ResultType
    fun manageData(result: ResultType)

    fun build(): ResourceManager<ResultType, RequestType>
    // fun asLiveData(): LiveData<Resource<ResultType>>
}