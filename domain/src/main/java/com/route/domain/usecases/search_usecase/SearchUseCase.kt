package com.route.domain.usecases.search_usecase

import com.route.domain.common.ResultWrapper
import com.route.domain.contract.repository.search_repo.SearchRepository
import com.route.domain.models.search.Search
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend fun getSearchMovies(search: String): Flow<ResultWrapper<List<Search>>> {
        return searchRepository.getSearchMovies(search = search)
    }
}