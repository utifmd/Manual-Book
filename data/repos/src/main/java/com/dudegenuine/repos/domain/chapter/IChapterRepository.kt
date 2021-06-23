package com.dudegenuine.repos.domain.chapter

import androidx.lifecycle.LiveData
import com.dudegenuine.domain.Chapter
import com.dudegenuine.repos.network.Resource
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 17/06/21.
 */
interface IChapterRepository{
    fun getChapters(param: Map<String, String>): Observable<List<Chapter>>
}