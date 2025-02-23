package com.mangabible.data.api

import com.mangabible.data.model.Data
import retrofit2.http.GET

interface ApiService {


    @GET("Anime")
    suspend fun getManga(): List<Data>
   }

