package com.mangabible.data.repository

import com.mangabible.data.model.MangaResponse

interface IMangaRepository {
    suspend fun fetchMangaInfo() : List<MangaResponse>
}