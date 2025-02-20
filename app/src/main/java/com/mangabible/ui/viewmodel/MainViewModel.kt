package com.mangabible.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangabible.data.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

        private val _state = MutableStateFlow<MainState>(MainState.Loading)
        val state: StateFlow<MainState>
            get() = _state

        fun fetchManga() {
            viewModelScope.launch {
                try {
                    val mangaData = repository.fetchMangaInfo()
                    _state.value = MainState.Success(mangaData.attributes.posterImage.tiny)
                } catch (e: Exception) {
                    _state.value = MainState.Error("Erro ao obter a lista de mangás: ${e.message}")
                }
            }
        }
    }
