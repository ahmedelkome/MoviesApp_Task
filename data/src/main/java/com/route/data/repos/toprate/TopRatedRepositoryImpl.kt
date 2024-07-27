package com.route.data.repos.toprate

import com.route.data.contract.toprated.online.TopRatedOnlineDataSource
import com.route.domain.common.ResultWrapper
import com.route.domain.contract.repository.toprated_repo.TopRatedRepository
import com.route.domain.models.toprated.TopRated
import com.route.domain.utils.toflow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopRatedRepositoryImpl @Inject constructor(
    private val topRatedOnlineDataSource: TopRatedOnlineDataSource
) : TopRatedRepository {
    override suspend fun getTopRatedMovies(): Flow<ResultWrapper<List<TopRated>>> {
        return toflow {
            topRatedOnlineDataSource.getTopRatedMovies()
        }
    }
}