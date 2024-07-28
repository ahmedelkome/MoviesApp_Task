package com.route.data.data_sources.search.offline

import com.route.data.contract.search.offline.SearchOfflineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun bindSearchOfflineDataSource(
        searchOfflineDataSourceImpl: SearchOfflineDataSourceImpl
    ): SearchOfflineDataSource
}