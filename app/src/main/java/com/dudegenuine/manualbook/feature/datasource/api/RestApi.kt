package com.dudegenuine.manualbook.feature.datasource.api

import com.dudegenuine.remote.model.ChapterResponse
import com.dudegenuine.remote.model.QuranResponse
import com.dudegenuine.remote.model.SearchResponse
import com.dudegenuine.remote.model.VerseResponse
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Manual Book created by utifmd on 17/06/21.
 */

interface RestApi {
    @GET("chapters")
    suspend fun getChapter(@QueryMap queries: Map<String, String>): ChapterResponse

    @GET("chapters/{chapter_id}/info?language=id")
    suspend fun getChapterInfo(@Path("chapter_id") chapterId: String): ChapterResponse

    @GET("search")
    suspend fun getSearch(@QueryMap queries: Map<String, String>): SearchResponse // Observable<SearchResponse>

    @GET("quran/verses/imlaei")
    suspend fun getQuran(@QueryMap queries: Map<String, String>): QuranResponse

    @GET("quran/recitations/1") //https://api.quran.com/api/v4/quran/recitations/1?page_number=3
    suspend fun getRecitations(@Query("page_number") pageNumber: String): QuranResponse

    @GET("verses/by_key/{verseKey}") // verses/by_key/1:1?language=id&words=false&translations=134&audio=1
    suspend fun getVerse(@Path("verseKey") verseKey: String, @QueryMap queries: Map<String, String>): VerseResponse
}