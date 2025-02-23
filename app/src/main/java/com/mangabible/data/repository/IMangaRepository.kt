package com.mangabible.data.repository

import com.mangabible.data.model.Data

interface IMangaRepository {
    suspend fun fetchMangaInfo() : List<Data>
}