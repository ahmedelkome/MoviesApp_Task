package com.route.data.data_sources.search.online

import android.util.Log
import com.route.data.api.WebService
import com.route.data.contract.search.online.SearchOnlineDataSource
import com.route.data.database.MyDataBase
import com.route.data.utils.Constants
import com.route.data.utils.safeData
import com.route.domain.models.search.Search
import com.route.domain.models.toprated.TopRated
import javax.inject.Inject

class SearchOnlineDataSourceImpl @Inject constructor(
    private val webService: WebService,
    private val myDataBase: MyDataBase
) : SearchOnlineDataSource {
    override suspend fun getSearchMovies(search: String): List<Search> {
        val list = myDataBase.searchDao().getAllSearch(search)
        val currentTime = System.currentTimeMillis()
        val timeDifferences = currentTime - validTimeSearch(list)
        return if (list != null && timeDifferences < Constants.EXPIRY_TIME) {
            list
        } else {
            safeData {
                myDataBase.searchDao().clearList()
                val isList = webService.getSearchMovies(search).results?.filterNotNull()!!.map {
                    it.toSearch()
                }
                Log.e("TAG", "getPopularMovies: list${isList}")
                myDataBase.searchDao().invalidateCache(Constants.EXPIRY_TIME)
                myDataBase.searchDao()
                    .insertAllSearch(
                        isList
                    )
                isList
            }
        }
    }
    fun validTimeSearch(search: List<Search>): Long {
        return if (search.isNullOrEmpty()) {
            0
        } else {
            search[0].timestamp
        }
    }
}