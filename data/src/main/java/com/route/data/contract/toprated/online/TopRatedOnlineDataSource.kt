package com.route.data.contract.toprated.online

import com.route.domain.models.toprated.TopRated

interface TopRatedOnlineDataSource {

    suspend fun getTopRatedMovies():List<TopRated>
}