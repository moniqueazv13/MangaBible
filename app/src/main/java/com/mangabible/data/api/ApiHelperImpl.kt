package com.mangabible.data.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

        override suspend fun fetchMangaInfo(id: String): String =
            apiService.getManga(id).data.attributes.description

    }
