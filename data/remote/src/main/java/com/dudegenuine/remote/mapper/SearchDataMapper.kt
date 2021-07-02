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
                query = pyd.query ?: "empty",
                totalPages = pyd.totalPages ?: 0,
                currentPage = pyd.currentPage ?: 0,
                totalResults = pyd.totalResults ?: 0,
                verseId = rsl?.verseId ?: 0,
                textOrig = rsl?.text ?: "empty",
                textTrans = rsl?.translations?.let { trs ->
                    if(trs.isNotEmpty())
                        trs.map { it?.text }.joinToString(separator = " , ")
                    else "No translation" } ?: "empty",
                nameTrans = rsl?.translations?.let { trs ->
                    if(trs.isNotEmpty())
                        trs.map { it?.name }.joinToString(separator = " , ")
                    else "No translation" } ?: "empty"
            ))}
        }

        return result
    }
}