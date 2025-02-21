package com.mangabible.data.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun fetchMangaInfo(): String =
        apiService.getManga().toString()
}

