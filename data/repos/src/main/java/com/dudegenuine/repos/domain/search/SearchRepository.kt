package com.dudegenuine.repos.domain.search

import androidx.lifecycle.LiveData
import com.dudegenuine.domain.Search
import com.dudegenuine.remote.mapper.SearchDataMapper
import com.dudegenuine.remote.payload.ISearchResponsePayload
import com.dudegenuine.remote.persistence.IChapterPersistence
import com.dudegenuine.remote.persistence.ISearchPersistence
import com.dudegenuine.repos.network.Resource
import com.dudegenuine.repos.network.ResourceManager
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Manual Book created by utifmd on 25/06/21.
 */
class SearchRepository @Inject constructor (
    // val local
    private val persistence: ISearchPersistence,
    private val mapper: SearchDataMapper ): ISearchRepository {

    override suspend fun getSearch(param: Map<String, String>): LiveData<Resource<List<Search>>> {
        return object: ResourceManager<List<Search>, ISearchResponsePayload>(){
            override fun shouldFetch(result: List<Search>?): Boolean {
                return true
            }

            override suspend fun fetchDataRemote(): ISearchResponsePayload {
                return persistence.getSearch(param)
            }

            override fun processRequest(response: ISearchResponsePayload): List<Search> {
                return mapper.convertSearchToList(response)
            }

            override fun saveData(result: List<Search>) {
                TODO("Not yet implemented")
            }

            override fun fetchDataLocal(): List<Search> {
                TODO("Not yet implemented")
            }
        }.build().commit()
    }
}