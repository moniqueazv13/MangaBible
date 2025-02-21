package com.mangabible.ui.viewmodel

sealed class MainState {
    data object Idle : MainState()
    data object Loading : MainState()
    data class Success(val mangaList: String) : MainState()
    data class Error(val message: String) : MainState()
}