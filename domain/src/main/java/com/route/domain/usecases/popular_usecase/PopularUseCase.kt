package com.route.domain.usecases.popular_usecase

import com.route.domain.common.ResultWrapper
import com.route.domain.contract.repository.popular_repo.PopularRepository
import com.route.domain.models.popular.Popular
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularUseCase @Inject constructor(
    private val popularRepository: PopularRepository
) {

    suspend fun getPopularMovies(): Flow<ResultWrapper<List<Popular>>> {
        return popularRepository.getPopularMovies()
    }
}