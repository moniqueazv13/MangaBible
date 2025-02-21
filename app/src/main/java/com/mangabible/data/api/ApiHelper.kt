package com.mangabible.data.api

import com.mangabible.data.model.MangaResponse

interface ApiHelper {
    suspend fun fetchMangaInfo(): List<MangaResponse>
}