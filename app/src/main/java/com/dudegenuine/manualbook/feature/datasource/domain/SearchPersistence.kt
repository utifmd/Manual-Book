package com.dudegenuine.manualbook.feature.datasource.domain

import com.dudegenuine.manualbook.feature.datasource.api.RestApi
import com.dudegenuine.remote.payload.ISearchResponsePayload
import com.dudegenuine.remote.persistence.ISearchPersistence
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 25/06/21.
 */
class SearchPersistence(private val restApi: RestApi): ISearchPersistence {

    override suspend fun getSearch(param: Map<String, String>): ISearchResponsePayload =
        restApi.getSearch(param)
}