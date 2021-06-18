package com.dudegenuine.data.remote.payload.api.chapter

import com.dudegenuine.data.remote.payload.contract.IChapterResponse
import com.google.gson.annotations.SerializedName

data class Chapter(
	val pages: List<Int?>? = null,
	val nameComplex: String? = null,
	val translatedName: TranslatedName? = null,
	@SerializedName("bismillahPre") val blhPre: Boolean? = null,
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

data class ChapterResponse(
	val data: List<Chapter>? = null): IChapterResponse