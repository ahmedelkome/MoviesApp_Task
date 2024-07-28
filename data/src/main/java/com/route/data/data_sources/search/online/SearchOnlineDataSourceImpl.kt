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
        myDataBase.searchDao().insertAllSearch(
            webService.getSearchMovies(search = search).results?.filterNotNull()!!.map {
                it.toSearch()
            }
        )
        val list = myDataBase.searchDao().getAllSearch(search)
        Log.e("TAG", "observeLiveData: Search list Online DataSource Dao${list}")
        val currentTime = System.currentTimeMillis() / 1000
        val timeDifferences = currentTime - list[0].timestamp
        if (list != null && list.isNotEmpty() && timeDifferences < Constants.EXPIRY_TIME) {
            list
        } else {
            myDataBase.searchDao().invalidateCache(Constants.EXPIRY_TIME)
            myDataBase.searchDao().replaceData(
                webService.getSearchMovies(search = search).results?.filterNotNull()!!.map {
                    it.toSearch()
                }
            )
        }
        return safeData {
            webService.getSearchMovies(search = search).results?.filterNotNull()!!.map {
                it.toSearch()
            }
        }
        Log.e("TAG", "observeLiveData: Search list online${getSearchMovies(search)}")
    }

}