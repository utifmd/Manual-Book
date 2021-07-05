package com.dudegenuine.manualbook.feature.datasource.domain

import com.dudegenuine.manualbook.feature.datasource.api.RestApi
import com.dudegenuine.remote.payload.IVerseResponsePayload
import com.dudegenuine.remote.persistence.IVersePersistence

/**
 * Manual Book created by utifmd on 02/07/21.
 */
class VersePersistence(val restApi: RestApi): IVersePersistence {
    override suspend fun getVerse(
        path: String,
        params: Map<String, String>): IVerseResponsePayload {

        return restApi.getVerse(path, params)
    }
}