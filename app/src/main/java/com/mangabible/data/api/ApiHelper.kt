package com.mangabible.data.api

import com.mangabible.data.model.MangaData

interface ApiHelper {
    suspend fun fetchMangaInfo(): MangaData
}