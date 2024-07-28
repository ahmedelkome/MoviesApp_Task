package com.route.data.repos.search

import com.route.data.contract.search.offline.SearchOfflineDataSource
import com.route.data.contract.search.online.SearchOnlineDataSource
import com.route.data.utils.ConnectivityChecker
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
        return toflow {
            if (ConnectivityChecker.isNetworkAvailable()) {
                searchOnlineDataSource.getSearchMovies(search)
            } else {
                searchOfflineDataSource.getAllSearch(search)
            }
        }
    }

}