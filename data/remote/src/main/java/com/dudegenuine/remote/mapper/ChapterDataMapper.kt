package com.dudegenuine.remote.mapper

import com.dudegenuine.domain.Chapter
import com.dudegenuine.remote.payload.IChapterResponsePayload
import com.dudegenuine.remote.model.ChapterResponse

/**
 * Manual Book created by utifmd on 17/06/21.
 */
class ChapterDataMapper {
    fun convertResponseToChapters(result: IChapterResponsePayload?): List<Chapter> {
        val response = mutableListOf<Chapter>()

        if(result is ChapterResponse){ // println("MAPPER ${result.data?.size}")
            result.data?.forEach { response.add( Chapter(
                id = it.id ?: 0,
                pages = listOf(it.pages?.get(0) ?: 1, it.pages?.get(1) ?: 1),
                // pages = it.pages?.joinToString(separator = " ~ ") ?: "empty", //, prefix = "", postfix = ""
                nameSimple = it.nameSimple ?: "empty",
                nameComplex = it.nameComplex ?: "empty",
                nameArabic = it.nameArabic ?: "empty",
                translatedName = it.translatedName?.name ?: "empty",
                versesCount = it.versesCount ?: 0,
                revelationPlace = it.revelationPlace ?: "empty",
            ))}
        }
        return response
    }

    fun convertResponseToChapter(response: IChapterResponsePayload, chapter: Chapter): Chapter {
        var result: Chapter? = null
        if (response is ChapterResponse){
            response.info?.let {
                result = Chapter( chapter,
                    infoSource = it.source ?: "empty",
                    infoText = it.text ?: "empty",
                    infoShortText = it.shortText ?: "empty"
                )
            }
        }

        return result!!
    }
}