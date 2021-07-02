package com.dudegenuine.remote.persistence

import com.dudegenuine.remote.payload.IQuranResponsePayload
import kotlinx.coroutines.Deferred

/**
 * Manual Book created by utifmd on 27/06/21.
 */
interface IQuranPersistence {
    suspend fun getQuran(param: Map<String, String>): IQuranResponsePayload
}