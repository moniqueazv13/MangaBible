package com.mangabible.data.api

interface ApiHelper {
    suspend fun fetchMangaInfo(id: String): String
}