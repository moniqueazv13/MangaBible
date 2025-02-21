package com.mangabible.data.api

import com.mangabible.data.model.Data
import com.mangabible.data.model.MangaResponse

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun fetchMangaInfo(): List<MangaResponse> =
        apiService.getManga()
}

