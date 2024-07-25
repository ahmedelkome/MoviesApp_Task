package com.route.data.contract.popular.online

import com.route.domain.models.popular.Popular

interface PopularOnlineDataSource {
    suspend fun getPopularMovies(): List<Popular>
}