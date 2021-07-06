package com.dudegenuine.domain

import java.io.Serializable

/**
 * Manual Book created by utifmd on 17/06/21.
 */

/*{
  "chapter_info": {
    "id": 1,
    "chapter_id": 1,
    "language_name": "english",
    "short_text": "This Surah is named Al-Fatihah because of its subject-matter. Fatihah is that which opens a subject or a book or any other thing. In other words, Al-Fatihah is a sort of preface.",
    "source": "Sayyid Abul Ala Maududi - Tafhim al-Qur'an - The Meaning of the Quran",
    "text": "<h2>Name</h2>\r\n<p>This Surah is named Al-Fatihah because of its subject-matter. Fatihah is that which opens a subject or a book or any other thing. In other words, Al-Fatihah is a sort of preface.</p>\r\n<h2>Period of Revelation</h2>..."
  }
}*/

data class Chapter(
    val id: Int,
    val pages: List<Int>,
    val nameSimple: String,
    val nameComplex: String,
    val translatedName: String,
    val versesCount: Int,
    val nameArabic: String,
    val revelationPlace: String): Serializable {

    constructor(chapter: Chapter, infoText: String, infoShortText: String, infoSource: String):
            this(chapter.id, chapter.pages, chapter.nameSimple, chapter.nameComplex, chapter.translatedName, chapter.versesCount, chapter.nameArabic, chapter.revelationPlace){
                defaultBody += "\n\n$infoText\nSumber: $infoSource"
            }

    var defaultBody: String =
        "Jumlah $versesCount ayat, secara etimologi berarti \"$translatedName\" $nameSimple diwahyukan kepada Nabi Muhammad SAW di kota $revelationPlace"

    /*
    * Preview
    * */

    val previewSender get() =
        "Qur\'an Surah - 14th century ago"

    val previewBody get() = defaultBody

    val previewPage get() =
        "Halaman ${pages.joinToString(separator = " hingga ")}"

    /*
    * View
    * */

    val viewName get() =
        "$nameSimple $nameArabic"

    val viewBody get() =
        "\"QS $nameComplex\" - $defaultBody"
}