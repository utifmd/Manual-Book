package com.dudegenuine.manualbook.feature.datasource.api

import com.dudegenuine.remote.model.ChapterResponse
import com.dudegenuine.remote.model.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Manual Book created by utifmd on 17/06/21.
 */

interface RestApi {
    @GET("chapters")
    fun getChapter(@QueryMap queryMap: Map<String, String>): Observable<ChapterResponse>

    @GET("search") /*https://api.quran.com/api/v4/search?q=yahudi&size=0&page=3&language=id*/
    fun getSearch(@QueryMap query: Map<String, String>): Observable<SearchResponse>
}