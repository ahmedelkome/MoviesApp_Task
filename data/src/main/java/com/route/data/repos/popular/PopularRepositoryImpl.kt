package com.route.data.repos.popular

import android.util.Log
import com.route.data.contract.popular.offline.PopularOfflineDataSource
import com.route.data.contract.popular.online.PopularOnlineDataSource
import com.route.data.utils.ConnectivityChecker
import com.route.data.utils.Constants
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
        val list = offlineDataSource.getAllPopular()
        val currenttime = System.currentTimeMillis()
        val diff = currenttime - validTimePopular(list)
        Log.e("TAG", "getPopularMovies current: ${currenttime}")
        Log.e("TAG", "getPopularMovies diff: ${diff}")
        Log.e("TAG", "getPopularMovies  valid: ${validTimePopular(list)}")
        Log.e("TAG", "getPopularMovies expiry: ${Constants.EXPIRY_TIME}")
        return toflow {
            if (diff < Constants.EXPIRY_TIME) {
                offlineDataSource.getAllPopular()
            } else {
                onlineDataSource.getPopularMovies()
            }
        }
    }

    fun validTimePopular(popular: List<Popular>): Long {
        return if (popular.isNullOrEmpty()) {
            0
        } else {
            popular[0].timestamp
        }
    }
}