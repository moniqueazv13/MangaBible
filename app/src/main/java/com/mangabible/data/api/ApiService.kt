package com.mangabible.data.api

import com.mangabible.data.model.KitsuResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

      @GET("manga/{id}")
      suspend fun getManga(@Path("id") mangaId: String): KitsuResponse
   }