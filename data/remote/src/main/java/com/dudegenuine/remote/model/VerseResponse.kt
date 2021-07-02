package com.dudegenuine.remote.model

data class VerseResponse(
	val verse: Verse? = null
)

data class TranslationVerse(
	val resourceId: Int? = null,
	val text: String? = null
)

data class Audio(
	val url: String? = null,
	val segments: List<String?>? = null
)

data class Verse(
	val verseKey: String? = null,
	val translations: List<TranslationVerse?>? = null,
	val sajdahNumber: Any? = null,
	val id: Int? = null,
	val sajdahType: Any? = null,
	val audio: Audio? = null,
	val verseNumber: Int? = null,
	val juzNumber: Int? = null,
	val rubNumber: Int? = null,
	val hizbNumber: Int? = null
)

