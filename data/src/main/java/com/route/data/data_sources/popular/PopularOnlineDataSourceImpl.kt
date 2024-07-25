package com.route.data.data_sources.popular

import com.route.data.api.WebService
import com.route.data.contract.popular.online.PopularOnlineDataSource
import com.route.data.utils.safeApi
import com.route.domain.models.popular.Popular
import javax.inject.Inject

class PopularOnlineDataSourceImpl @Inject constructor(
    private val webService: WebService
) : PopularOnlineDataSource {
    override suspend fun getPopularMovies(): List<Popular> {
        return safeApi {
            webService.getPopularMovies().results?.filterNotNull()!!.map {
                it.toPopular()
            }
        }
    }
}