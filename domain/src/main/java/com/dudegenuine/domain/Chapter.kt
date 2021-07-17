package com.dudegenuine.domain
import java.io.Serializable

/**
 * Manual Book created by utifmd on 17/06/21.
 */
data class Chapter(
    val id: Int,
    val pages: List<Int>,
    val nameSimple: String,
    val nameComplex: String,
    val translatedName: String,
    val versesCount: Int,
    val nameArabic: String,
    val revelationPlace: String,
    val infoText: String? = null,
    val infoShortText: String? = null,
    val infoSource: String? = null ): Serializable {

    companion object {
        const val TAG_KEY: String = "chapter"
    }

    constructor(chapter: Chapter, infoText: String, infoShortText: String, infoSource: String): this(
        chapter.id, chapter.pages, chapter.nameSimple, chapter.nameComplex, chapter.translatedName,
        chapter.versesCount, chapter.nameArabic, chapter.revelationPlace, infoText, infoShortText, infoSource
    ){
        defaultBody += "\n\n$infoText\nSumber: $infoSource"
    }

    /*
    * Preview
    * */

    val previewSender get() =
        "Qur\'an Surah - 14th century ago"

    val previewBody get() = defaultBody

    val previewPage get() =
        "Halaman ke-${pages.joinToString(separator = " dari ")} halaman"

    /*
    * View
    * */

    var defaultBody: String =
        "Jumlah $versesCount ayat, secara etimologi berarti \"$translatedName\" $nameSimple diwahyukan kepada Nabi Muhammad SAW di kota $revelationPlace"

    val viewName get() =
        "$nameSimple $nameArabic"

    val viewBody get() =
        "\"QS $nameComplex\" - $defaultBody"
}