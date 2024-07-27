package com.route.data.contract.search.online

import com.route.domain.models.search.Search

interface SearchOnlineDataSource {

    suspend fun getSearchMovies(search: String): List<Search>
}