package com.dudegenuine.remote.persistence

import com.dudegenuine.remote.payload.ISearchResponsePayload
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 25/06/21.
 */
interface ISearchPersistence {
    suspend fun getSearch(param: Map<String, String>): ISearchResponsePayload
}