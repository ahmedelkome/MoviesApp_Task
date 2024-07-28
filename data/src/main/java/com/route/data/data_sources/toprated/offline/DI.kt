package com.route.data.data_sources.toprated.offline

import com.route.data.contract.toprated.offline.TopRatedOfflineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun bindTopRatedOfflineDataSource(
        topRatedOfflineDataSourceImpl: TopRatedOfflineDataSourceImpl
    ):TopRatedOfflineDataSource
}