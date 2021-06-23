package com.dudegenuine.repos.domain.chapter

import androidx.lifecycle.LiveData
import com.dudegenuine.domain.Chapter
import com.dudegenuine.remote.mapper.ChapterDataMapper
import com.dudegenuine.remote.payload.IChapterResponse
import com.dudegenuine.remote.persistence.IChapterPersistence
import com.dudegenuine.repos.network.Resource
import com.dudegenuine.repos.network.ResourceManager
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 17/06/21.
 */
class ChapterRepository(
    private val persistence: IChapterPersistence,
    private val mapper: ChapterDataMapper ) {

    fun getChapters(param: Map<String, String>): LiveData<Resource<List<Chapter>>> { //Resource<List<Chapter>> {
        return object: ResourceManager<List<Chapter>, IChapterResponse>(){
            override fun shouldFetch(result: List<Chapter>?): Boolean {
                return true
            }

            override fun fetchDataRemote(): Observable<out IChapterResponse> {
                return persistence.getChapter(param)
            }

            override fun processRequest(response: IChapterResponse): List<Chapter> {
                return mapper.convertChapterToList(response)
            }

            override fun saveData(result: List<Chapter>) {
                TODO("Not yet implemented")
            }

            override fun fetchDataLocal(): List<Chapter> {
                TODO("Not yet implemented")
            }

        }.build().asLiveData()
    }
}