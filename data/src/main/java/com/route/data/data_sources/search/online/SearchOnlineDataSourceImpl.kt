package com.route.data.data_sources.search.online

import android.util.Log
import com.route.data.api.WebService
import com.route.data.contract.search.online.SearchOnlineDataSource
import com.route.data.database.MyDataBase
import com.route.data.utils.Constants
import com.route.data.utils.safeData
import com.route.domain.models.search.Search
import javax.inject.Inject

class SearchOnlineDataSourceImpl @Inject constructor(
    private val webService: WebService,
    private val myDataBase: MyDataBase
) : SearchOnlineDataSource {
    override suspend fun getSearchMovies(search: String): List<Search> {
        val list = myDataBase.searchDao().getAllSearch(search)
        val currentTime = System.currentTimeMillis() / 1000
        val timeDifferences = currentTime - list.last().timestamp
        if (list != null && timeDifferences < Constants.EXPIRY_TIME) {
            return list
        } else return safeData {
            myDataBase.searchDao().invalidateCache(Constants.EXPIRY_TIME)
            myDataBase.searchDao()
                .insertAllSearch(webService.getSearchMovies(search).results?.filterNotNull()!!.map {
                    it.toSearch()
                })
            myDataBase.searchDao().updateCacheTimestamp(currentTime)
            webService.getSearchMovies(search = search).results?.filterNotNull()!!.map {
                it.toSearch()
            }
        }
    }
}