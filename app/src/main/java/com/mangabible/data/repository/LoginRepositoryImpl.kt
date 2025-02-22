package com.mangabible.data.repository

class LoginRepositoryImpl : ILoginRepository {
    override suspend fun login(username: String, password: String): Boolean {
        return true
    }


    override fun isLoggedIn(): Boolean {
        return false
    }
}