package com.dudegenuine.repos.domain.chapter

import androidx.lifecycle.LiveData
import com.dudegenuine.domain.Chapter
import com.dudegenuine.repos.network.Resource

/**
 * Manual Book created by utifmd on 17/06/21.
 */
interface IChapterRepository{
    suspend fun getChapters(param: Map<String, String>): LiveData<Resource<List<Chapter>>>
}