package com.mangabible.ui.viewmodel.splash

import androidx.lifecycle.ViewModel
import com.mangabible.data.repository.LoginRepositoryImpl

class SplashViewModel(private val repository: LoginRepositoryImpl) : ViewModel() {
    fun isLoggedIn() = repository.isLoggedIn()
}
