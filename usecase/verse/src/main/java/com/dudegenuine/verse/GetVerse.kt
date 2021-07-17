package com.dudegenuine.verse

import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.dudegenuine.domain.Verse
import com.dudegenuine.repos.domain.verse.IVerseRepository
import com.dudegenuine.repos.network.Resource

/**
 * Manual Book created by utifmd on 04/07/21.
 */
class GetVerse(private val repository: IVerseRepository) {
    suspend operator fun invoke(path: String, params: Map<String, String>): LiveData<Resource<Verse>>{
        return Transformations.map(repository.getVerse(path, params)){ it }
    }
}