package com.dudegenuine.remote.persistence

import com.dudegenuine.remote.payload.IChapterResponsePayload
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 17/06/21.
 */
interface IChapterPersistence {
    fun getChapter(param: Map<String, String>): Observable<out IChapterResponsePayload>
}