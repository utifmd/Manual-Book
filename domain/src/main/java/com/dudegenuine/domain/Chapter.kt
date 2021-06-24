package com.dudegenuine.domain

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
    val revelationPlace: String
){
    val previewSender get() =
        "Qur\'an Surah - a century ago"
    val viewName get() =
        "$nameSimple $nameArabic"
    val previewBody get() =
        "QS $nameComplex \"$nameArabic\" $versesCount ayat, secara etimologi berarti \"$translatedName\" $nameSimple diwahyukan kepada Nabi Muhammad SAW di kota $revelationPlace"
    val previewPage get() =
        "halaman ${pages[0]}/${pages[1]}"
}