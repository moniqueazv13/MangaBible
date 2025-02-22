package com.mangabible.ui.intent

sealed class LoginIntent {
    data class UsernameChanged(val username: String) : LoginIntent()
    data class PasswordChanged(val password: String) : LoginIntent()
    data object LoginButtonClicked : LoginIntent()
}
