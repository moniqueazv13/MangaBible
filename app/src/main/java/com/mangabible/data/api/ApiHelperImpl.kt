package com.mangabible.data.api

import com.mangabible.data.model.response.Data

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun fetchMangaInfo(): List<Data> =
        apiService.getManga()
}

