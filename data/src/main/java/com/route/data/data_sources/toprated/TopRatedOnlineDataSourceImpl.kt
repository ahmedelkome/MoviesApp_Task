package com.route.data.data_sources.toprated

import com.route.data.api.WebService
import com.route.data.contract.toprated.online.TopRatedOnlineDataSource
import com.route.data.utils.safeData
import com.route.domain.models.toprated.TopRated
import javax.inject.Inject

class TopRatedOnlineDataSourceImpl @Inject constructor(
    private val webService: WebService
) : TopRatedOnlineDataSource {
    override suspend fun getTopRatedMovies(): List<TopRated> {
        return safeData {
            webService.getTopRatedMovies().results?.filterNotNull()!!.map {
                it.toRated()
            }
        }
    }

}