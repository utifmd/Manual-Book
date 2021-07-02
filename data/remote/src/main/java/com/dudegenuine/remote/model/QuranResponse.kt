package com.dudegenuine.remote.model

import com.dudegenuine.remote.payload.IQuranResponsePayload
import com.google.gson.annotations.SerializedName

data class QuranResponse(
	val verses: List<VersesItem?>? = null
): IQuranResponsePayload

data class VersesItem(
	@SerializedName("verse_key")
	val verseKey: String? = null,
	@SerializedName("text_imlaei")
	val text: String? = null,
	val id: Int? = null
)