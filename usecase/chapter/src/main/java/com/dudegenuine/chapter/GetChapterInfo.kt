package com.dudegenuine.chapter

import android.os.Build
import android.text.Html
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.dudegenuine.domain.Chapter
import com.dudegenuine.repos.domain.chapter.IChapterRepository
import com.dudegenuine.repos.network.Resource

/**
 * Manual Book created by utifmd on 06/07/21.
 */
class GetChapterInfo(private val repository: IChapterRepository) {
    suspend operator fun invoke(chapter: Chapter): LiveData<Resource<Chapter>> =
        Transformations.map(repository.getChapterInfo(chapter)) { it }
}