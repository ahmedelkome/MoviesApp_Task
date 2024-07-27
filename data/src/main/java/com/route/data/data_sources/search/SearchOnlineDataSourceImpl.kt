package com.route.data.data_sources.search

import com.route.data.api.WebService
import com.route.data.contract.search.online.SearchOnlineDataSource
import com.route.data.utils.safeApi
import com.route.domain.models.search.Search
import javax.inject.Inject

class SearchOnlineDataSourceImpl @Inject constructor(
    private val webService: WebService
) : SearchOnlineDataSource {
    override suspend fun getSearchMovies(search: String): List<Search> {
        return safeApi {
            webService.getSearchMovies(search = search).results?.filterNotNull()!!.map {
                it.toSearch()
            }
        }
    }

}