package com.dudegenuine.manualbook.feature.datasource.domain

import com.dudegenuine.data.remote.payload.contract.IChapterResponse
import com.dudegenuine.data.remote.persistance.contract.IChapterPersistence
import com.dudegenuine.manualbook.feature.datasource.api.ChapterApi
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 17/06/21.
 */
// injected api
class ChapterPersistence(
    private var chapterApi: ChapterApi
): IChapterPersistence {

    override fun getChapter(param: Map<String, String>): Observable<out IChapterResponse> =
        chapterApi.getChapter(param)
}