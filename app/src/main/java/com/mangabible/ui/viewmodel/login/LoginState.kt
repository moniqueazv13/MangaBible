package com.mangabible.ui.viewmodel.login

sealed class LoginState {
    data object Loading : LoginState()
    data object Success : LoginState()
    data class Error(val message: String) : LoginState()
    data object Idle : LoginState()
}
