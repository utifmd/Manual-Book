package com.dudegenuine.remote.model

import com.dudegenuine.remote.payload.IChapterResponse
import com.google.gson.annotations.SerializedName


data class ChapterResponse(
	@SerializedName("chapters")
	val data: List<Chapter>? = null): IChapterResponse

data class Chapter(
	val id: Int? = null,
	val pages: List<Int?>? = null,

	@SerializedName("name_complex")
	val nameComplex: String? = null,

	@SerializedName("name_simple")
	val nameSimple: String? = null,

	@SerializedName("translated_name")
	val translatedName: TranslatedName? = null,

	@SerializedName("bismillah_pre")
    val blhPre: Boolean? = null,

	@SerializedName("revelation_order")
	val revelationOrder: Int? = null,

	@SerializedName("verses_count")
	val versesCount: Int? = null,

	@SerializedName("arabic_name")
	val nameArabic: String? = null,

	@SerializedName("revelation_place")
	val revelationPlace: String? = null
)

data class TranslatedName(
	val name: String? = null,

	@SerializedName("language_name")
	val languageName: String? = null
)