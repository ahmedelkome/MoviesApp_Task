package com.route.data.repos.popular

import com.route.data.contract.popular.online.PopularOnlineDataSource
import com.route.domain.common.ResultWrapper
import com.route.domain.contract.repository.popular_repo.PopularRepository
import com.route.domain.models.popular.Popular
import com.route.domain.utils.toflow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val onlineDataSource: PopularOnlineDataSource
) : PopularRepository {
    override suspend fun getPopularMovies(): Flow<ResultWrapper<List<Popular>>> {
        return toflow {
            onlineDataSource.getPopularMovies()
        }
    }
}