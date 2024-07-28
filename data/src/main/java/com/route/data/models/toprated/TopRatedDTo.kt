package com.route.data.models.toprated

import com.google.gson.annotations.SerializedName
import com.route.data.utils.Constants
import com.route.domain.models.toprated.TopRated

data class TopRatedDTo(

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("origin_country")
    val originCountry: List<String?>? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("original_name")
    val originalName: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("adult")
    val adult: Boolean? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null
) {
    fun toRated(): TopRated {
        return TopRated(
            title = originalName,
            overView = overview,
            vote = voteAverage,
            posterPath = Constants.ORIGINALPATHFORIMAGES + posterPath,
            timestamp = System.currentTimeMillis()
        )
    }
}