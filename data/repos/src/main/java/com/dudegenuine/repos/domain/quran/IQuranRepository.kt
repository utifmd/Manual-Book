package com.dudegenuine.repos.domain.quran

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.dudegenuine.domain.Quran
import com.dudegenuine.repos.network.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Manual Book created by utifmd on 27/06/21.
 */
interface IQuranRepository {
     // suspend fun getQuran(param: Map<String, Int>): LiveData<PagingData<Quran>>
     // suspend fun getQuran(param: Map<String, String>): LiveData<Resource<PagingData<Quran>>>
     fun getQuran(param: Map<String, Int>): Flow<PagingData<Quran>>
}