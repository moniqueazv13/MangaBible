package com.mangabible.ui.viewmodel.splash

import androidx.lifecycle.ViewModel
import com.mangabible.data.repository.ILoginRepository
import com.mangabible.data.repository.LoginRepositoryImpl

class SplashViewModel(private val repository: LoginRepositoryImpl) : ViewModel() {
    fun isLoggedIn(): Boolean {
        // Here you should check if the user is logged in.
        // For example, you can check if there is a token stored in the shared preferences.
        // For now, we will return true.
        return repository.isLoggedIn()
    }
}