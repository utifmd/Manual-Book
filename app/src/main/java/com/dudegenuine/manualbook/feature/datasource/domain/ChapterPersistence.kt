package com.dudegenuine.manualbook.feature.datasource.domain

import com.dudegenuine.manualbook.feature.datasource.api.RestApi
import com.dudegenuine.remote.payload.IChapterResponsePayload
import com.dudegenuine.remote.persistence.IChapterPersistence
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 17/06/21.
 */

// injected api
class ChapterPersistence(private val restApi: RestApi): IChapterPersistence {

    override suspend fun getChapter(param: Map<String, String>): IChapterResponsePayload = //Observable<out IChapterResponsePayload> =
        restApi.getChapter(param)

    /*override suspend fun getChapterInfo(path: String): IChapterResponsePayload =
        restApi.getChapterInfo(path)*/
}
