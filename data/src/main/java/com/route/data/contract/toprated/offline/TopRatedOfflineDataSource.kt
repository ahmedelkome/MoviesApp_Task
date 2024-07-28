package com.route.data.contract.toprated.offline

import com.route.domain.models.toprated.TopRated

interface TopRatedOfflineDataSource {
    suspend fun getAllTopRated(): List<TopRated>
}