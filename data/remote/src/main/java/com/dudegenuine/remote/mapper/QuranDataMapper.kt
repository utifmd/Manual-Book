package com.dudegenuine.remote.mapper

import com.dudegenuine.domain.Quran
import com.dudegenuine.remote.model.QuranResponse
import com.dudegenuine.remote.payload.IQuranResponsePayload

/**
 * Manual Book created by utifmd on 27/06/21.
 */
class QuranDataMapper {
    fun convertQuranToList(persistence: IQuranResponsePayload?): List<Quran>{
        val result: MutableList<Quran> = mutableListOf()

        if (persistence is QuranResponse){
            persistence.verses?.forEach { result.add( Quran(
                id = it?.id ?: 0,
                verseKey = it?.verseKey ?: "empty",
                text = it?.text ?: "empty"
            ))}
        }

        return result
    }
}