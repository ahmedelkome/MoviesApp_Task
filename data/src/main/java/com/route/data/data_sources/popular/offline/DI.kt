package com.route.data.data_sources.popular.offline

import com.route.data.contract.popular.offline.PopularOfflineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun bindPopularOfflineDataSource(
        popularOfflineDataSourceImpl: PopularOfflineDataSourceImpl
    ): PopularOfflineDataSource
}