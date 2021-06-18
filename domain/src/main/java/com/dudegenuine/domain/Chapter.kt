package com.dudegenuine.domain

/**
 * Manual Book created by utifmd on 17/06/21.
 */
data class Chapter (
    val id: Int,
    val pages: List<Int?>,
    val nameComplex: String,
    val translatedName: String,
    val versesCount: Int,
    val nameArabic: String,
    val revelationPlace: String
)