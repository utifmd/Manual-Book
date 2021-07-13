package com.dudegenuine.quran

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.dudegenuine.domain.Quran
import com.dudegenuine.repos.domain.quran.QuranRepository
import com.dudegenuine.repos.network.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Manual Book created by utifmd on 27/06/21.
 */
class GetQuran(private val repository: QuranRepository) {

    /*operator fun invoke(param: Map<String, Int>): Flow<PagingData<Quran>>{
        return repository.getQuran(param)
    }*/

    suspend operator fun invoke(param: Map<String, Int>): LiveData<PagingData<Quran>>{
        return Transformations.map(repository.getQuran(param)){ it }
    }

    /*fun flowPagingData(param: Map<String, Int>): Flow<PagingData<Quran>>{
        return repository.getQuran(param)
    }*/
}