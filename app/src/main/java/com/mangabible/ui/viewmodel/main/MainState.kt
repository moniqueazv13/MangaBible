package com.mangabible.ui.viewmodel.main

import com.mangabible.ui.MangaVO

sealed class MainState {
    data object Idle : MainState()
    data object Loading : MainState()
    data class Success(val mangaList: List<MangaVO>) : MainState()
    data class Error(val message: String) : MainState()
}