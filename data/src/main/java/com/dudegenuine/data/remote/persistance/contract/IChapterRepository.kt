package com.dudegenuine.data.remote.persistance.contract

import com.dudegenuine.domain.Chapter
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 17/06/21.
 */
interface IChapterRepository{
    fun getChapters(param: Map<String, String>): Observable<List<Chapter>>
}