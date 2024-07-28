package com.route.domain.contract.repository.toprated_repo

import com.route.domain.common.ResultWrapper
import com.route.domain.models.toprated.TopRated
import kotlinx.coroutines.flow.Flow

interface TopRatedRepository {
    suspend fun getTopRatedMovies():Flow<ResultWrapper<List<TopRated>>>
}