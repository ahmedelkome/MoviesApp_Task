package com.route.data.data_sources.popular.online

import com.route.data.api.WebService
import com.route.data.contract.popular.online.PopularOnlineDataSource
import com.route.data.database.daos.popular.PopularDao
import com.route.data.utils.Constants
import com.route.data.utils.safeData
import com.route.domain.models.popular.Popular
import javax.inject.Inject

class PopularOnlineDataSourceImpl @Inject constructor(
    private val webService: WebService,
    private val popularDao: PopularDao
) : PopularOnlineDataSource {
    override suspend fun getPopularMovies(): List<Popular> {
        popularDao.insertAllPopular(webService.getPopularMovies().results?.filterNotNull()!!.map {
            it.toPopular()
        })
        val list = popularDao.getAllPopular()
        val currentTime = System.currentTimeMillis() / 1000
        val timeDifferences = list.map { currentTime - it.timestamp } as Long
        if (list != null && timeDifferences < Constants.EXPIRY_TIME) {
            list
        } else {
            popularDao.invalidateCache(Constants.EXPIRY_TIME)
            popularDao.insertAllPopular(
                webService.getPopularMovies().results?.filterNotNull()!!.map {
                    it.toPopular()
                })
        }
        return safeData {
            webService.getPopularMovies().results?.filterNotNull()!!.map {
                it.toPopular()
            }
        }

    }
}