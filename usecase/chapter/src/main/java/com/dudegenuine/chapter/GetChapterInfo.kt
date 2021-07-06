package com.dudegenuine.chapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dudegenuine.domain.Chapter
import com.dudegenuine.repos.domain.chapter.IChapterRepository
import com.dudegenuine.repos.network.Resource

/**
 * Manual Book created by utifmd on 06/07/21.
 */
class GetChapterInfo(private val repository: IChapterRepository) {
    suspend operator fun invoke(chapter: Chapter): LiveData<Resource<Chapter>> =
        Transformations.map(repository.getChapterInfo(chapter)){ it }
}