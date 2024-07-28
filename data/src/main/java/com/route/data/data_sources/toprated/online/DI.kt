package com.route.data.data_sources.toprated.online

import com.route.data.contract.toprated.online.TopRatedOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun bindTopRatedOnlineDataSource(
        topRatedOnlineDataSourceImpl: TopRatedOnlineDataSourceImpl
    ): TopRatedOnlineDataSource
}