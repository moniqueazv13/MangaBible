package com.mangabible.ui.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangabible.data.repository.LoginRepositoryImpl
import com.mangabible.ui.intent.LoginIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepositoryImpl: LoginRepositoryImpl) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.UsernameChanged -> {
                _state.update { it.copy(username = intent.username) }
            }
            is LoginIntent.PasswordChanged -> {
                _state.update { it.copy(password = intent.password) }
            }
            LoginIntent.LoginButtonClicked -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val result = loginRepositoryImpl.login(state.value.username, state.value.password)
                if (result) {
                    _state.update { it.copy(isLoading = false, isLoggedIn = true) }
                } else {
                    _state.update { it.copy(isLoading = false, errorMessage = "Login falhou") }
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, errorMessage = "Erro de conexão") }
            }
        }
    }
}