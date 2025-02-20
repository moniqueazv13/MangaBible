package com.mangabible.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

    private const val CLIENT_ID = ""
    private const val CLIENT_SECRET = ""

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val authInterceptor = AuthInterceptor(CLIENT_ID, CLIENT_SECRET)

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://kitsu.io/api/edge/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

    val apiService = retrofit.create(ApiService::class.java)
    val apiHelper = ApiHelperImpl(apiService)

    class AuthInterceptor(private val clientId: String, private val clientSecret: String) :
        Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            println("AuthInterceptor está sendo chamado!") // Adicione esta linha
            val request = chain.request().newBuilder()
                .addHeader("Client-ID", clientId)
                .addHeader("Client-Secret", clientSecret)
                .build()
            return chain.proceed(request)
        }
    }
}