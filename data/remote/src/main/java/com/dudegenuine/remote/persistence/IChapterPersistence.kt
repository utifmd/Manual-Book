package com.dudegenuine.remote.persistence

import com.dudegenuine.remote.payload.IChapterResponsePayload
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 17/06/21.
 */
interface IChapterPersistence {
    suspend fun getChapter(param: Map<String, String>): IChapterResponsePayload // Observable<out IChapterResponsePayload>
    suspend fun getChapterInfo(path: String): IChapterResponsePayload
}