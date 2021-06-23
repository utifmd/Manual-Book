package com.dudegenuine.chapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dudegenuine.domain.Chapter
import com.dudegenuine.repos.domain.chapter.ChapterRepository
import com.dudegenuine.repos.network.Resource

class GetChapters( private var repository: ChapterRepository) {

    operator fun invoke(param: Map<String, String>): LiveData<Resource<List<Chapter>>>{
        return Transformations.map(repository.getChapters(param)){ it }
    }
}