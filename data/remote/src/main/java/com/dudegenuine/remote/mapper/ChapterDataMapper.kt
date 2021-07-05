package com.dudegenuine.remote.mapper

import com.dudegenuine.domain.Chapter
import com.dudegenuine.remote.payload.IChapterResponsePayload
import com.dudegenuine.remote.model.ChapterResponse

/**
 * Manual Book created by utifmd on 17/06/21.
 */
class ChapterDataMapper {
    fun convertChapterToList(result: IChapterResponsePayload?): List<Chapter> {
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
                /*infoShortText = result.info?.shortText ?: "empty",
                infoText = result.info?.text ?: "empty",
                infoSource = result.info?.source ?: "empty"*/
            ))}
        }
        return response
    }
}