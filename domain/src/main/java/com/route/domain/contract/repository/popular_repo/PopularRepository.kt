package com.route.domain.contract.repository.popular_repo

import com.route.domain.common.ResultWrapper
import com.route.domain.models.popular.Popular
import kotlinx.coroutines.flow.Flow

interface PopularRepository {

    suspend fun getPopularMovies(): Flow<ResultWrapper<List<Popular>>>
}