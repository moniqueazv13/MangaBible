package com.mangabible.ui.viewmodel.splash

import androidx.lifecycle.ViewModel
import com.mangabible.data.repository.ILoginRepository

class SplashViewModel(private val loginRepository: ILoginRepository) : ViewModel() {
    fun isLoggedIn() = loginRepository.isLoggedIn()

}