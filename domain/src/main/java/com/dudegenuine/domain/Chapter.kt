package com.dudegenuine.domain

import java.io.Serializable

/**
 * Manual Book created by utifmd on 17/06/21.
 */
data class Chapter (
    val id: Int,
    val pages: List<Int?>,
    val nameSimple: String,
    val nameComplex: String,
    val translatedName: String,
    val versesCount: Int,
    val nameArabic: String,
    val revelationPlace: String ): Serializable {

    private val defaultBody get() =
        "$versesCount ayat, secara etimologi berarti \"$translatedName\" $nameSimple diwahyukan kepada Nabi Muhammad SAW di kota $revelationPlace"

    /*
    * Preview
    * */

    val previewSender get() =
        "Qur\'an Surah - 14th century ago"

    val previewBody get() =
        "$nameArabic $defaultBody"

    val previewPage get() =
        "Halaman ${pages[0]}/${pages[1]}"

    /*
    * View
    * */

    val viewName get() =
        "$nameSimple $nameArabic"

    val viewBody get() =
        "\"QS $nameComplex\" - $defaultBody"
}