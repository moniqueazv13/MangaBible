package com.mangabible.ui.intent

sealed class LoginIntent {
    data class Login(val username: String, val password: String) : LoginIntent()
    data class Register(val username: String, val password: String) : LoginIntent()
}
