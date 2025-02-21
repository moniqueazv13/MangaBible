package com.mangabible.data.api

import com.example.myapplication.data.model.MangaResponse
import retrofit2.http.GET

interface ApiService {


    @GET("Anime")
    suspend fun getManga(): List<MangaResponse>
   }

