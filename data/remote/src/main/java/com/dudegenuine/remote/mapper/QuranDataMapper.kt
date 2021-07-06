package com.dudegenuine.remote.mapper

import com.dudegenuine.domain.Quran
import com.dudegenuine.remote.model.QuranResponse
import com.dudegenuine.remote.payload.IQuranResponsePayload

/**
 * Manual Book created by utifmd on 27/06/21.
 */
class QuranDataMapper {
    fun convertRequestQuranList(persisQuran: IQuranResponsePayload?, persisAudio: IQuranResponsePayload?): List<Quran>{
        val result: MutableList<Quran> = mutableListOf()

        if (persisQuran is QuranResponse && persisAudio is QuranResponse){
            persisQuran.verses?.forEach { quran ->
                val audioFile = persisAudio.audioFiles?.first { it?.verseKey == quran?.verseKey }

                result.add( Quran(
                    id = quran?.id ?: 0,
                    verseKey = quran?.verseKey ?: "empty",
                    text = quran?.text ?: "empty",
                    audioUrl = audioFile?.url ?: "empty",
                    audioReciterName = persisAudio.meta?.reciterName ?: "empty",
                    audioReciterStyle = persisAudio.meta?.recitationStyle.toString()
                ))
            }
        }
        return result
    }
}