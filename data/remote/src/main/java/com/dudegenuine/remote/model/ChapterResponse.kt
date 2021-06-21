package com.dudegenuine.remote.model

import com.dudegenuine.remote.payload.IChapterResponse
import com.google.gson.annotations.SerializedName


data class ChapterResponse(
	val data: List<Chapter>? = null): IChapterResponse

data class Chapter(
	val pages: List<Int?>? = null,
	val nameComplex: String? = null,
	val translatedName: TranslatedName? = null,
	@SerializedName("bismillahPre")
    val blhPre: Boolean? = null,
	val revelationOrder: Int? = null,
	val versesCount: Int? = null,
	val id: Int? = null,
	val nameArabic: String? = null,
	val revelationPlace: String? = null
)

data class TranslatedName(
	val name: String? = null,
	val languageName: String? = null
)