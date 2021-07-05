package com.dudegenuine.repos.domain.verse

import androidx.lifecycle.LiveData
import com.dudegenuine.domain.Verse
import com.dudegenuine.repos.network.Resource

/**
 * Manual Book created by utifmd on 04/07/21.
 */
interface IVerseRepository {
    suspend fun getVerse(path: String, params: Map<String, String>): LiveData<Resource<Verse>>
}