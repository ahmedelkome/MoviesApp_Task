package com.route.data.api

import com.route.data.utils.Constants
import com.route.data.models.popular.PopularResponse
import com.route.data.models.toprated.TopRatedResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface WebService {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): PopularResponse

    @GET("/3/tv/top_rated")
    suspend fun getTopRatedMovies():TopRatedResponse
}