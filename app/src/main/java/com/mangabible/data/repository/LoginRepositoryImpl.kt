package com.mangabible.data.repository

class LoginRepositoryImpl : ILoginRepository {
    override suspend fun login(username: String, password: String): Boolean {
        // Aqui você faria a chamada para a API de login.
        // Exemplo:
        // val response = apiService.login(username, password)
        // return response.isSuccessful
        // Por enquanto, vamos simular um login bem-sucedido.
        return true
    }
}