package com.route.data.data_sources.popular.online

import com.route.data.api.WebService
import com.route.data.contract.popular.online.PopularOnlineDataSource
import com.route.data.database.MyDataBase
import com.route.data.database.daos.popular.PopularDao
import com.route.data.utils.Constants
import com.route.data.utils.safeData
import com.route.domain.models.popular.Popular
import javax.inject.Inject

class PopularOnlineDataSourceImpl @Inject constructor(
    private val webService: WebService,
    private val myDataBase: MyDataBase
) : PopularOnlineDataSource {
    override suspend fun getPopularMovies(): List<Popular> {
        val list = myDataBase.popularDao().getAllPopular()
        val currentTime = System.currentTimeMillis() / 1000
        val timeDifferences = currentTime - list.last().timestamp
        if (list != null && timeDifferences < Constants.EXPIRY_TIME) {
            return list
        } else {
            return safeData {
                myDataBase.popularDao().invalidateCache(Constants.EXPIRY_TIME)
                myDataBase.popularDao()
                    .insertAllPopular(webService.getPopularMovies().results?.filterNotNull()!!.map {
                        it.toPopular()
                    })
                myDataBase.popularDao().updateCacheTimestamp(currentTime)
                webService.getPopularMovies().results?.filterNotNull()!!.map {
                    it.toPopular()
                }
            }
        }
    }
}