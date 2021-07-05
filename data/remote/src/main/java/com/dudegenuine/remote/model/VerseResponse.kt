package com.dudegenuine.remote.model

import com.dudegenuine.remote.payload.IVerseResponsePayload
import com.google.gson.annotations.SerializedName

data class VerseResponse(
	val verse: Verse? = null
): IVerseResponsePayload

data class Verse(
	val id: Int? = null,
	@SerializedName("verse_key")
	val verseKey: String? = null,
	val translations: List<TranslationVerse?>? = null,
	@SerializedName("sajdah_number")
	val sjdNumber: Any? = null,
	@SerializedName("sajdah_type")
	val sjdType: Any? = null,
	val audio: Audio? = null,
	@SerializedName("verse_number")
	val verseNumber: Int? = null,
	@SerializedName("juz_number")
	val juzNumber: Int? = null,
	@SerializedName("rub_number")
	val rubNumber: Int? = null,
	@SerializedName("hisb_number")
	val hzbNumber: Int? = null
)

data class TranslationVerse(
	@SerializedName("resource_id")
	val resourceId: Int? = null,
	val text: String? = null
)

data class Audio(
	val url: String? = null,
	val segments: List<List<Int?>?>? = null
)

