package com.dudegenuine.manualbook.feature.datasource.domain

import com.dudegenuine.manualbook.feature.datasource.api.RestApi
import com.dudegenuine.remote.payload.IQuranResponsePayload
import com.dudegenuine.remote.persistence.IQuranPersistence
import kotlinx.coroutines.Deferred

/**
 * Manual Book created by utifmd on 27/06/21.
 */
class QuranPersistence(private val restApi: RestApi): IQuranPersistence {
    override suspend fun getQuran(param: Map<String, String>): IQuranResponsePayload {
        return restApi.getQuran(param)
    }
}