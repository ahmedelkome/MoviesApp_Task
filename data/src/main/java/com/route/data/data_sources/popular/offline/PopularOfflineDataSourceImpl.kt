package com.route.data.data_sources.popular.offline

import com.route.data.contract.popular.offline.PopularOfflineDataSource
import com.route.data.database.daos.popular.PopularDao
import com.route.data.utils.safeData
import com.route.domain.models.popular.Popular
import javax.inject.Inject

class PopularOfflineDataSourceImpl @Inject constructor(
    private val popularDao: PopularDao
) : PopularOfflineDataSource {
    override suspend fun getAllPopular(): List<Popular> {
        return safeData { popularDao.getAllPopular() }
    }
}