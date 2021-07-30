package com.dudegenuine.repos.domain.chapter

import androidx.lifecycle.LiveData
import com.dudegenuine.domain.Chapter
import com.dudegenuine.remote.mapper.ChapterDataMapper
import com.dudegenuine.remote.payload.IChapterResponsePayload
import com.dudegenuine.remote.persistence.IChapterPersistence
import com.dudegenuine.repos.network.Resource
import com.dudegenuine.repos.network.ResourceManager
import javax.inject.Inject

/**
 * Manual Book created by utifmd on 17/06/21.
 */
class ChapterRepository @Inject constructor (
    private val persistence: IChapterPersistence,
    private val mapper: ChapterDataMapper): IChapterRepository {

    override suspend fun getChapters(param: Map<String, String>): LiveData<Resource<List<Chapter>>> {
        return object: ResourceManager<List<Chapter>, IChapterResponsePayload>() {
            override fun shouldFetch(result: List<Chapter>?): Boolean {
                return true
            }

            override suspend fun fetchDataRemote(): IChapterResponsePayload {
                return persistence.getChapter(param)
            }

            override fun processRequest(response: IChapterResponsePayload): List<Chapter> {
                return mapper.convertResponseToChapters(response)
            }

            override fun saveData(result: List<Chapter>) {
                TODO("Not yet implemented")
            }

            override fun fetchDataLocal(): List<Chapter> {
                TODO("Not yet implemented")
            }

        }.build().commit()
    }

    override suspend fun getChapterInfo(chapter: Chapter): LiveData<Resource<Chapter>> {
        return object: ResourceManager<Chapter, IChapterResponsePayload>(){
            override fun shouldFetch(result: Chapter?): Boolean  = true

            override suspend fun fetchDataRemote(): IChapterResponsePayload =
                persistence.getChapterInfo(chapter.id.toString())

            override fun processRequest(response: IChapterResponsePayload): Chapter =
                mapper.convertResponseToChapter(response, chapter)

            override fun saveData(result: Chapter) {
                TODO("Not yet implemented")
            }

            override fun fetchDataLocal(): Chapter {
                TODO("Not yet implemented")
            }

        }.build().commit()
    }
}