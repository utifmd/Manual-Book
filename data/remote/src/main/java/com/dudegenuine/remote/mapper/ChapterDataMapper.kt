package com.dudegenuine.remote.mapper

import com.dudegenuine.domain.Chapter
import com.dudegenuine.remote.payload.IChapterResponse
import com.dudegenuine.remote.model.ChapterResponse

/**
 * Manual Book created by utifmd on 17/06/21.
 */
class ChapterDataMapper {
    fun convertChapterList(result: IChapterResponse?): List<Chapter> {
        val response = mutableListOf<Chapter>()

        if(result is ChapterResponse){
            result.data?.forEach { response.add (
                Chapter(
                    id = it.id ?: 0,
                    pages = it.pages ?: emptyList(),
                    nameComplex = it.nameComplex ?: "",
                    nameArabic = it.nameArabic ?: "",
                    translatedName = it.translatedName?.name ?: "",
                    versesCount = it.versesCount ?: 0,
                    revelationPlace = it.revelationPlace ?: ""
                )
            ) }
        }

        return response // Resource(Resource.Status.SUCCESS, data = response)
    }

}