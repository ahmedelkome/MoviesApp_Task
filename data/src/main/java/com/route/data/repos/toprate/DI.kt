package com.route.data.repos.toprate

import com.route.domain.contract.repository.toprated_repo.TopRatedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {
    @Binds
    abstract fun bindTopRateRepository(
        topRatedRepositoryImpl: TopRatedRepositoryImpl
    ): TopRatedRepository
}