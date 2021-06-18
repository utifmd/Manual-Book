package com.dudegenuine.data.remote.persistance.repository

import com.dudegenuine.data.remote.persistance.contract.IChapterRepository
import com.dudegenuine.data.remote.persistance.contract.IChapterPersistence
import com.dudegenuine.data.remote.persistance.mapper.ChapterDataMapper
import com.dudegenuine.domain.Chapter
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 17/06/21.
 */
class ChapterRepository(
    private val persistence: IChapterPersistence,
    private val mapper: ChapterDataMapper): IChapterRepository {

    override fun getChapters(param: Map<String, String>): Observable<List<Chapter>> {
        return persistence.getChapter(param).map {
            mapper.convertChapterList(it)
        }
    }
}