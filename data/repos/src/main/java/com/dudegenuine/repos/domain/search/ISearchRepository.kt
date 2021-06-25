package com.dudegenuine.repos.domain.search

import androidx.lifecycle.LiveData
import com.dudegenuine.domain.Search
import com.dudegenuine.repos.network.Resource
import io.reactivex.Observable

/**
 * Manual Book created by utifmd on 25/06/21.
 */
interface ISearchRepository {
    fun getSearch(param: Map<String, String>): LiveData<Resource<List<Search>>>
}