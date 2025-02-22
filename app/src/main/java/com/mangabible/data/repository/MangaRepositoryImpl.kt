package com.mangabible.data.repository

import com.mangabible.data.api.ApiHelper

class MangaRepositoryImpl(private val apiHelper: ApiHelper) : IMangaRepository {
    override suspend fun fetchMangaInfo() = apiHelper.fetchMangaInfo()
}