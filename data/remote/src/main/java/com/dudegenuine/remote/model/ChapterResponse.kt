package com.dudegenuine.remote.model

import com.dudegenuine.remote.payload.IChapterResponsePayload
import com.google.gson.annotations.SerializedName


data class ChapterResponse(
	@SerializedName("chapters")
	val data: List<Chapter>? = null,

	@SerializedName("chapter_info")
	val info: ChapterInfo? = null
): IChapterResponsePayload

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

	@SerializedName("name_arabic")
	val nameArabic: String? = null,

	@SerializedName("revelation_place")
	val revelationPlace: String? = null
)

data class TranslatedName(
	val name: String? = null,

	@SerializedName("language_name")
	val languageName: String? = null
)

data class ChapterInfo(
	val id: Int? = null,
	@SerializedName("chapter_id")
	val chapterId: Int? = null,
	@SerializedName("language_name")
	val langName: String? = null,
	@SerializedName("short_text")
	val shortText: String? = null,
	val source: String? = null,
	val text: String? = null
)