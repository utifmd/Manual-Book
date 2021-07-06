package com.dudegenuine.remote.model

import com.dudegenuine.remote.payload.IQuranResponsePayload
import com.google.gson.annotations.SerializedName

data class QuranResponse(
	val verses: List<VersesItem?>? = null,
	@SerializedName("audio_files")
	val audioFiles: List<AudioFilesItem?>? = null,
	val meta: Meta? = null
): IQuranResponsePayload

data class VersesItem(
	@SerializedName("verse_key")
	val verseKey: String? = null,
	@SerializedName("text_imlaei")
	val text: String? = null,
	val id: Int? = null
)

data class Meta(
	@SerializedName("reciter_name")
	val reciterName: String? = null,
	@SerializedName("recitation_style")
	val recitationStyle: Any? = null
)

data class AudioFilesItem(
	@SerializedName("verse_key")
	val verseKey: String? = null,
	val url: String? = null
)