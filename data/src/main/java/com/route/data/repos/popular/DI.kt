package com.route.data.repos.popular

import com.route.domain.contract.repository.popular_repo.PopularRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun bindPopularRepository(
        popularRepositoryImpl: PopularRepositoryImpl
    ): PopularRepository
}