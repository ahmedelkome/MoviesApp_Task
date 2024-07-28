package com.route.data.models.toprated

import com.google.gson.annotations.SerializedName

data class TopRatedResponse(

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<TopRatedDTo?>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)