package com.dudegenuine.remote.mapper

import com.dudegenuine.domain.Verse
import com.dudegenuine.remote.model.VerseResponse
import com.dudegenuine.remote.payload.IVerseResponsePayload

/**
 * Manual Book created by utifmd on 02/07/21.
 */
class VerseDataMapper {

    fun convertVerseToItem(payload: IVerseResponsePayload): Verse {
        return with (payload as VerseResponse){
            Verse(
                id = this.verse?.id ?: 0,
                verseKey = this.verse?.verseKey ?: "empty",
                audioUrl = this.verse?. audio?.url ?: "empty",
                verseNumber = this.verse?.verseNumber ?: 0,
                juzNumber = this.verse?.juzNumber ?: 0,
                rubNumber = this.verse?.rubNumber ?: 0,
                hzbNumber = this.verse?.hzbNumber ?: 0,
                translationText = this.verse?.translations?.get(0)?.text ?: "empty"
            )
        }
    }
}