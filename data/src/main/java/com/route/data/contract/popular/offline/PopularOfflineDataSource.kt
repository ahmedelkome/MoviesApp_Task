package com.route.data.contract.popular.offline

import com.route.domain.models.popular.Popular

interface PopularOfflineDataSource {

    suspend fun getAllPopular(): List<Popular>
}