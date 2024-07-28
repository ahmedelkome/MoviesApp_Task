package com.route.domain.contract.repository.search_repo

import com.route.domain.common.ResultWrapper
import com.route.domain.models.search.Search
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun getSearchMovies(search: String): Flow<ResultWrapper<List<Search>>>
}