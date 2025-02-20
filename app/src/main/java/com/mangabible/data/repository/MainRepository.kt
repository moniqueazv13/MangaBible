package com.mangabible.data.repository

import com.mangabible.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun fetchMangaInfo() = apiHelper.fetchMangaInfo("14916")
}