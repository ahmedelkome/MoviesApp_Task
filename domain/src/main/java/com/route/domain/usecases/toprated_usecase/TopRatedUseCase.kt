package com.route.domain.usecases.toprated_usecase

import com.route.domain.common.ResultWrapper
import com.route.domain.contract.repository.toprated_repo.TopRatedRepository
import com.route.domain.models.toprated.TopRated
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopRatedUseCase @Inject constructor(
    private val topRatedRepository: TopRatedRepository
) {
    suspend fun getTopRatedMovies(): Flow<ResultWrapper<List<TopRated>>> {
        return topRatedRepository.getTopRatedMovies()
    }
}