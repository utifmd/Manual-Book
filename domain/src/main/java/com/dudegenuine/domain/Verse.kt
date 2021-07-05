package com.dudegenuine.domain

import java.io.Serializable


/**
 * Manual Book created by utifmd on 02/07/21.
 */
data class Verse(
    val id: Int,
    val verseKey: String,
    val translationText: String,
    val audioUrl: String,
    val verseNumber: Int,
    val juzNumber: Int,
    val rubNumber: Int,
    val hzbNumber: Int
): Serializable {

    val viewVerseNumber
        get() = "Ayat ke-$verseNumber | Hizb $hzbNumber | Rub $rubNumber"
}