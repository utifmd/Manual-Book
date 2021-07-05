package com.dudegenuine.domain

import java.io.Serializable

/**
 * Manual Book created by utifmd on 27/06/21.
 */

data class Quran(
    val id: Int,
    val verseKey: String,
    val text: String
): Serializable