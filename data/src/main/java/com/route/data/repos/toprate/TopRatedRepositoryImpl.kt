package com.route.data.repos.toprate

import com.route.data.contract.toprated.offline.TopRatedOfflineDataSource
import com.route.data.contract.toprated.online.TopRatedOnlineDataSource
import com.route.data.utils.ConnectivityChecker
import com.route.data.utils.Constants
import com.route.domain.common.ResultWrapper
import com.route.domain.contract.repository.toprated_repo.TopRatedRepository
import com.route.domain.models.toprated.TopRated
import com.route.domain.utils.toflow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopRatedRepositoryImpl @Inject constructor(
    private val topRatedOnlineDataSource: TopRatedOnlineDataSource,
    private val topRatedOfflineDataSource: TopRatedOfflineDataSource
) : TopRatedRepository {
    override suspend fun getTopRatedMovies(): Flow<ResultWrapper<List<TopRated>>> {
        val list = topRatedOfflineDataSource.getAllTopRated()
        val currenttime = System.currentTimeMillis() / 1000
        val diff = currenttime - validTimeTopRated(list)
        return toflow {
            if (diff > Constants.EXPIRY_TIME ) {
                topRatedOnlineDataSource.getTopRatedMovies()
            } else {
                topRatedOfflineDataSource.getAllTopRated()
            }
        }
    }

    fun validTimeTopRated(topRated: List<TopRated>): Long {
        if (topRated.isNullOrEmpty()) {
            return 0
        } else {
            return topRated[0].timestamp
        }
    }
}