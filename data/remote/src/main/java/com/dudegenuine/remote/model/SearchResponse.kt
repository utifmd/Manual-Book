package com.dudegenuine.remote.model

import com.dudegenuine.remote.payload.ISearchResponsePayload
import com.google.gson.annotations.SerializedName

data class SearchResponse(val search: Search? = null): ISearchResponsePayload

data class Search(
	val query: String? = null,
	@SerializedName("total_pages")
	val totalPages: Int? = null,
	val results: List<ResultsItem?>? = null,
	@SerializedName("current_page")
	val currentPage: Int? = null,
	@SerializedName("total_results")
	val totalResults: Int? = null
)

data class ResultsItem(
	@SerializedName("translations")
	val translations: List<TranslationSearch?>? = null,
	@SerializedName("verse_id")
	val verseId: Int? = null,
	val text: String? = null
)

data class TranslationSearch(
	val name: String? = null,
	val id: Int? = null,
	val text: String? = null
)