package com.dudegenuine.data.remote.persistance.contract

import com.dudegenuine.data.remote.payload.contract.IChapterResponse
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 17/06/21.
 */
interface IChapterPersistence {
    fun getChapter(param: Map<String, String>): Observable<out IChapterResponse>
}