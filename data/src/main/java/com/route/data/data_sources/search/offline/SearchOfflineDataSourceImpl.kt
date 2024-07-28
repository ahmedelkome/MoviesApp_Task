package com.route.data.data_sources.search.offline

import com.route.data.contract.search.offline.SearchOfflineDataSource
import com.route.data.database.MyDataBase
import com.route.domain.models.search.Search
import javax.inject.Inject

class SearchOfflineDataSourceImpl
@Inject constructor(private val myDataBase: MyDataBase) : SearchOfflineDataSource {
    override suspend fun getAllSearch(title: String): List<Search> {
        return myDataBase.searchDao().getAllSearch(title = title)
    }
}