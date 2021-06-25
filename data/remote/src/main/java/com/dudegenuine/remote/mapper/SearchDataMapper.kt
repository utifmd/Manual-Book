package com.dudegenuine.remote.mapper

import com.dudegenuine.domain.Search
import com.dudegenuine.remote.model.SearchResponse
import com.dudegenuine.remote.payload.ISearchResponsePayload

/**
 * Manual Book created by utifmd on 25/06/21.
 */
class SearchDataMapper {
    fun convertSearchToList(payload: ISearchResponsePayload?): List<Search>{
        val result = mutableListOf<Search>()

        if (payload is SearchResponse){
            val pyd = payload.search

            pyd?.results?.map { rsl -> result.add( Search(
                query = pyd.query ?: "",
                totalPages = pyd.totalPages ?: 0,
                currentPage = pyd.currentPage ?: 0,
                totalResults = pyd.totalResults ?: 0,
                verseId = rsl?.verseId ?: 0,
                textOrig = rsl?.text ?: "",
                textTrans = rsl?.translations?.get(0)?.text ?: "",
                nameTrans = rsl?.translations?.get(0)?.name ?: ""
            ))}
        }

        return result
    }
}