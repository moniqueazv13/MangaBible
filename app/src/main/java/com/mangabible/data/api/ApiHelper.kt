package com.mangabible.data.api

interface ApiHelper {
    suspend fun getAnimeList(id: String): String
}