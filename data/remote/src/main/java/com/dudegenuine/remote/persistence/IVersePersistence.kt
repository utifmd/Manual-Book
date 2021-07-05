package com.dudegenuine.remote.persistence

import com.dudegenuine.remote.payload.IVerseResponsePayload

/**
 * Manual Book created by utifmd on 02/07/21.
 */
interface IVersePersistence {
    suspend fun getVerse(path: String, params: Map<String, String>): IVerseResponsePayload
}