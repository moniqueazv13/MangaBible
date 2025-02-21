package com.mangabible.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.MainRepository
import com.mangabible.ui.intent.MainIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState> = _state

    fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.FetchManga -> fetchManga()
        }
    }

    private fun fetchManga() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            try {
                val mangaList = repository.fetchMangaInfo()
                _state.value = MainState.Success(mangaList.toString())
            } catch (e: Exception) {
                _state.value = MainState.Error("Error: ${e.message}")
            }
        }
    }
}