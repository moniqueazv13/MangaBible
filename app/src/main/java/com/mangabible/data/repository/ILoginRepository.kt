package com.mangabible.data.repository

interface ILoginRepository {
    suspend fun login(username: String, password: String): Boolean
}