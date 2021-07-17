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

    constructor(verse: Verse, translationText: String): this(
        verse.id, verse.verseKey, translationText, verse.audioUrl, verse.verseNumber, verse.juzNumber, verse.rubNumber, verse.hzbNumber
    )

    val viewVerseNumber
        get() = "Ayat ke-$verseNumber | Hizb $hzbNumber | Rub $rubNumber"
}