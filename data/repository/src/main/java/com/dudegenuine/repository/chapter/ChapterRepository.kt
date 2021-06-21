package com.dudegenuine.repository.chapter

import com.dudegenuine.domain.Chapter
import com.dudegenuine.remote.mapper.ChapterDataMapper
import com.dudegenuine.remote.persistence.IChapterPersistence
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 17/06/21.
 */
class ChapterRepository(
    private val persistence: IChapterPersistence,
    private val mapper: ChapterDataMapper ): IChapterRepository {

    override fun getChapters(param: Map<String, String>): Observable<List<Chapter>> {
        return persistence.getChapter(param).map {
            mapper.convertChapterList(it)
        }
    }
}