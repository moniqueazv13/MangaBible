package com.mangabible.data.api

import com.mangabible.data.model.Data

interface ApiHelper {
    suspend fun fetchMangaInfo(): List<Data>
}