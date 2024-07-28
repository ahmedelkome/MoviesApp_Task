package com.route.data.data_sources.toprated.offline

import com.route.data.contract.toprated.offline.TopRatedOfflineDataSource
import com.route.data.database.daos.toprated.TopRatedDao
import com.route.data.utils.safeData
import com.route.domain.models.toprated.TopRated
import javax.inject.Inject

class TopRatedOfflineDataSourceImpl @Inject constructor(
    private val topRatedDao: TopRatedDao
) : TopRatedOfflineDataSource {
    override suspend fun getAllTopRated(): List<TopRated> {
        return safeData { topRatedDao.getAllTopRated() }
    }
}