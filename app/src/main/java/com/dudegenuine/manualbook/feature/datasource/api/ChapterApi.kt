package com.dudegenuine.manualbook.feature.datasource.api

import com.dudegenuine.remote.model.ChapterResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Manual Book created by utifmd on 17/06/21.
 */

interface ChapterApi {
    @GET("chapters")
    fun getChapter(@QueryMap queryMap: Map<String, String>): Observable<ChapterResponse>
}