package com.route.data.api

import com.route.data.utils.Constants
import com.route.data.models.popular.PopularResponse
import com.route.data.models.search.SearchResponse
import com.route.data.models.toprated.TopRatedResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WebService {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): PopularResponse

    @GET("/3/tv/top_rated")
    suspend fun getTopRatedMovies(): TopRatedResponse

    @GET("/3/search/movie")
    suspend fun getSearchMovies(
        @Query("query") search: String
    ): SearchResponse
}