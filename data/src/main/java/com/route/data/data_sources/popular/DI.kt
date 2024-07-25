package com.route.data.data_sources.popular

import com.route.data.contract.popular.online.PopularOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun bindPopularOnlineDataSource(
        popularOnlineDataSourceImpl: PopularOnlineDataSourceImpl
    ):
            PopularOnlineDataSource
}