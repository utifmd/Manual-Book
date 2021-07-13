package com.dudegenuine.domain

import java.io.Serializable

/**
 * Manual Book created by utifmd on 27/06/21.
 */

data class Quran(
    val id: Int,
    val verseKey: String,
    val audioUrl: String,
    val audioReciterName: String,
    val audioReciterStyle: String,
    val text: String
): Serializable{
    companion object {
        const val TAG_KEY = "quran"
    }
}