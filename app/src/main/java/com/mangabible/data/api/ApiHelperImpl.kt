package com.mangabible.data.api

import com.mangabible.data.model.MangaData

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

        override suspend fun fetchMangaInfo(): MangaData =
            apiService.getManga().data
    }
