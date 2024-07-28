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
        myDataBase.popularDao().replaceData(webService.getPopularMovies().results?.filterNotNull()!!.map {
            it.toPopular()
        })
        val list = myDataBase.popularDao().getAllPopular()
        val currentTime = System.currentTimeMillis() / 1000
        val timeDifferences = currentTime - list.get(0).timestamp
        if (list != null && timeDifferences < Constants.EXPIRY_TIME) {
            list
        } else {
            myDataBase.popularDao().invalidateCache(Constants.EXPIRY_TIME)
            myDataBase.popularDao().insertAllPopular(
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