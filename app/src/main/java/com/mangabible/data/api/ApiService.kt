package com.mangabible.data.api

import com.mangabible.data.model.KitsuResponse
import retrofit2.http.GET

interface ApiService {

      @GET("trending/anime")
      suspend fun getManga(): KitsuResponse
   }