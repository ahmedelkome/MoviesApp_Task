package com.route.data.repos.popular

import com.route.data.contract.popular.offline.PopularOfflineDataSource
import com.route.data.contract.popular.online.PopularOnlineDataSource
import com.route.data.utils.ConnectivityChecker
import com.route.domain.common.ResultWrapper
import com.route.domain.contract.repository.popular_repo.PopularRepository
import com.route.domain.models.popular.Popular
import com.route.domain.utils.toflow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val onlineDataSource: PopularOnlineDataSource,
    private val offlineDataSource: PopularOfflineDataSource
) : PopularRepository {
    override suspend fun getPopularMovies(): Flow<ResultWrapper<List<Popular>>> {
        return toflow {
            if (ConnectivityChecker.isNetworkAvailable()
            ) {
                onlineDataSource.getPopularMovies()
            } else if (offlineDataSource.getAllPopular()
                    .isEmpty()
            ) {
                onlineDataSource.getPopularMovies()
            } else {
                offlineDataSource.getAllPopular()
            }

        }
    }
}