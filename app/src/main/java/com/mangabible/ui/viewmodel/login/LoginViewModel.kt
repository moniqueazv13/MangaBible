package com.mangabible.ui.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangabible.data.model.entity.User
import com.mangabible.data.repository.UserRepositoryImpl
import com.mangabible.ui.intent.LoginIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepositoryImpl) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.Login -> login(intent.username, intent.password)
            is LoginIntent.Register -> register(intent.username, intent.password)
        }
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            userRepository.getUserByUsername(username).collect { user ->
                if (user != null && checkPassword(password, user.passwordHash)) {
                    _state.value = LoginState.Success
                } else {
                    _state.value = LoginState.Error("Invalid username or password")
                }
            }
        }
    }

    private fun register(username: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            userRepository.getUserByUsername(username).collect { user ->
                if (user == null) {
                    val passwordHash = hashPassword(password)
                    val newUser = User(username = username, passwordHash = passwordHash)
                    userRepository.insertUser(newUser)
                    _state.value = LoginState.Success
                } else {
                    _state.value = LoginState.Error("Username already exists")
                }
            }
        }
    }


    private fun hashPassword(password: String): String {
        // Implement password hashing here (e.g., using a library like Argon2)
        return password
    }

    private fun checkPassword(password: String, passwordHash: String): Boolean {
        // Implement password checking here (e.g., comparing the hashed password)
        return password == passwordHash
    }
}