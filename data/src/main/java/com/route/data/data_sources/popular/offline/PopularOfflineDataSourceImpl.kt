package com.route.data.data_sources.popular.offline

import com.route.data.contract.popular.offline.PopularOfflineDataSource
import com.route.data.database.MyDataBase
import com.route.data.database.daos.popular.PopularDao
import com.route.data.utils.safeData
import com.route.domain.models.popular.Popular
import javax.inject.Inject

class PopularOfflineDataSourceImpl @Inject constructor(
    private val myDataBase: MyDataBase
) : PopularOfflineDataSource {
    override suspend fun getAllPopular(): List<Popular> {
        return safeData {
            myDataBase.popularDao().getAllPopular()
        }
    }
}