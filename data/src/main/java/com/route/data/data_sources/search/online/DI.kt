package com.route.data.data_sources.search.online

import com.route.data.contract.search.online.SearchOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun bindSearchOnlineDataSource(
        searchOnlineDataSourceImpl: SearchOnlineDataSourceImpl
    ): SearchOnlineDataSource
}