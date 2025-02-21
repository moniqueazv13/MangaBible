package com.mangabible.data.api

interface ApiHelper {
    suspend fun fetchMangaInfo(): String
}