package com.route.data.contract.search.offline

import com.route.domain.models.search.Search

interface SearchOfflineDataSource {

    suspend fun getAllSearch(title:String):List<Search>
}