package com.dudegenuine.repos.domain.chapter

import com.dudegenuine.domain.Chapter
import com.dudegenuine.remote.mapper.ChapterDataMapper
import com.dudegenuine.remote.model.ChapterResponse
import com.dudegenuine.remote.persistence.IChapterPersistence
import com.dudegenuine.repos.network.Resource
import com.dudegenuine.repos.network.ResourceManager
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 17/06/21.
 */
class ChapterRepository(
    private val persistence: IChapterPersistence,
    private val mapper: ChapterDataMapper ){ // : IChapterRepository {

    fun getChapters(param: Map<String, String>): Resource<List<Chapter>> { //LiveData<Resource<List<Chapter>>> {
        return object: ResourceManager<List<Chapter>, ChapterResponse>(){
            override fun shouldFetch(result: List<Chapter>?): Boolean {
                return true
            }

            override fun fetchDataRemote(): Observable<ChapterResponse> {
                return persistence.getChapter(param).map { it as ChapterResponse }
            }

            override fun processRequest(response: ChapterResponse): List<Chapter> {
                return mapper.convertChapterList(response)
            }

            override fun saveData(result: List<Chapter>) {
                TODO("Not yet implemented")
            }

            override fun fetchDataLocal(): List<Chapter> {
                TODO("Not yet implemented")
            }

            override fun manageData(result: List<Chapter>) {
                TODO("Not yet implemented")
            }

        }.build().asData()
    }
}