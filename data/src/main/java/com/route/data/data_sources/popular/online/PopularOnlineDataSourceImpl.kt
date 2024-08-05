package com.route.data.data_sources.popular.online

import android.util.Log
import com.route.data.api.WebService
import com.route.data.contract.popular.online.PopularOnlineDataSource
import com.route.data.database.MyDataBase
import com.route.data.database.daos.popular.PopularDao
import com.route.data.utils.Constants
import com.route.data.utils.safeData
import com.route.domain.models.popular.Popular
import com.route.domain.models.search.Search
import javax.inject.Inject

class PopularOnlineDataSourceImpl @Inject constructor(
    private val webService: WebService,
    private val myDataBase: MyDataBase
) : PopularOnlineDataSource {
    override suspend fun getPopularMovies(): List<Popular> {
        val list = myDataBase.popularDao().getAllPopular()
        val currentTime = System.currentTimeMillis()
        val timeDifferences = currentTime - validTimePopular(list)
        return if (list != null && timeDifferences < Constants.EXPIRY_TIME) {
            list
        } else {
            safeData {
                myDataBase.popularDao().clearList()
                val isList = webService.getPopularMovies().results?.filterNotNull()!!.map {
                    it.toPopular()
                }
                Log.e("TAG", "getPopularMovies: list${isList}")
                myDataBase.popularDao().invalidateCache(Constants.EXPIRY_TIME)
                myDataBase.popularDao()
                    .insertAllPopular(
                        isList
                    )
                isList
            }

        }

    }

    fun validTimePopular(popular: List<Popular>): Long {
        return if (popular.isNullOrEmpty()) {
            0
        } else {
            popular[0].timestamp
        }
    }
}
