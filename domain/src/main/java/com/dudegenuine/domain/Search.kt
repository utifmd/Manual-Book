package com.dudegenuine.domain

import java.io.Serializable

/**
 * Manual Book created by utifmd on 25/06/21.
 */
data class Search (
    val query: String,
    val totalPages: Int,
    val currentPage: Int,
    val totalResults: Int,
    val verseId: Int,
    val textOrig: String,
    val nameTrans: String,
    val textTrans: String
): Serializable {
    val previewTitle get() = nameTrans
}