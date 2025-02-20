package com.mangabible.data.repository

import com.mangabible.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getMangaList() = apiHelper.getAnimeList("14916")
}