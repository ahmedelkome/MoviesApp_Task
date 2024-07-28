package com.route.data.data_sources.toprated.online

import com.route.data.api.WebService
import com.route.data.contract.toprated.online.TopRatedOnlineDataSource
import com.route.data.database.MyDataBase
import com.route.data.database.daos.toprated.TopRatedDao
import com.route.data.utils.Constants
import com.route.data.utils.safeData
import com.route.domain.models.toprated.TopRated
import javax.inject.Inject

class TopRatedOnlineDataSourceImpl @Inject constructor(
    private val webService: WebService,
    private val myDataBase: MyDataBase
) : TopRatedOnlineDataSource {
    override suspend fun getTopRatedMovies(): List<TopRated> {
        myDataBase.topRatedDao()
            .replaceData(webService.getTopRatedMovies().results?.filterNotNull()!!.map {
                it.toRated()
            })
        val list = myDataBase.topRatedDao().getAllTopRated()
        val currentTime = System.currentTimeMillis() / 1000
        val timeDifferences = currentTime - list.get(0).timestamp
        if (list != null && timeDifferences < Constants.EXPIRY_TIME) {
            list
        } else {
            myDataBase.topRatedDao().invalidateCache(Constants.EXPIRY_TIME)
            myDataBase.topRatedDao().insertAllTopRated(
                webService.getTopRatedMovies().results?.filterNotNull()!!.map {
                    it.toRated()
                })
        }
        return safeData {
            webService.getTopRatedMovies().results?.filterNotNull()!!.map {
                it.toRated()
            }
        }
    }

}