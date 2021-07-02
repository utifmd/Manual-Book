package com.dudegenuine.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dudegenuine.domain.Search
import com.dudegenuine.repos.domain.search.SearchRepository
import com.dudegenuine.repos.network.Resource

/**
 * Manual Book created by utifmd on 25/06/21.
 */
class GetSearches(private val repository: SearchRepository) {
    suspend operator fun invoke(param: Map<String, String>): LiveData<Resource<List<Search>>> {
        return Transformations.map(
            repository.getSearch(param)
        ){ it }
    }
}