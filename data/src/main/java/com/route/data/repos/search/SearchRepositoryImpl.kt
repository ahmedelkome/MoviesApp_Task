package com.route.data.repos.search

import com.route.data.contract.search.offline.SearchOfflineDataSource
import com.route.data.contract.search.online.SearchOnlineDataSource
import com.route.data.utils.ConnectivityChecker
import com.route.data.utils.Constants
import com.route.domain.common.ResultWrapper
import com.route.domain.contract.repository.search_repo.SearchRepository
import com.route.domain.models.search.Search
import com.route.domain.utils.toflow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchOnlineDataSource: SearchOnlineDataSource,
    private val searchOfflineDataSource: SearchOfflineDataSource
) : SearchRepository {
    override suspend fun getSearchMovies(search: String): Flow<ResultWrapper<List<Search>>> {
        val list = searchOfflineDataSource.getAllSearch(search)
        val currenttime = System.currentTimeMillis()
        val diff = currenttime - validTimeSearch(list)
        return toflow {
            if (diff < Constants.EXPIRY_TIME ) {
                searchOfflineDataSource.getAllSearch(search)
            } else {
                searchOnlineDataSource.getSearchMovies(search)
            }
        }
    }

    fun validTimeSearch(search: List<Search>): Long {
        return if (search.isNullOrEmpty()) {
            0
        } else {
            search[0].timestamp
        }
    }
}