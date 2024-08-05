package com.route.data.data_sources.toprated.online

import android.util.Log
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
        val list = myDataBase.topRatedDao().getAllTopRated()
        val currentTime = System.currentTimeMillis()
        val timeDifferences = currentTime - validTimeTopRated(list)
        return if (list != null && timeDifferences < Constants.EXPIRY_TIME) {
            list
        } else {
            safeData {
                myDataBase.topRatedDao().clearList()
                val isList = webService.getTopRatedMovies().results?.filterNotNull()!!.map {
                    it.toRated()
                }
                Log.e("TAG", "getPopularMovies: list${isList}")
                myDataBase.topRatedDao().invalidateCache(Constants.EXPIRY_TIME)
                myDataBase.topRatedDao()
                    .insertAllTopRated(
                        isList
                    )
                isList
            }
        }
    }

    fun validTimeTopRated(topRated: List<TopRated>): Long {
        if (topRated.isNullOrEmpty()) {
            return 0
        } else {
            return topRated[0].timestamp
        }
    }
}