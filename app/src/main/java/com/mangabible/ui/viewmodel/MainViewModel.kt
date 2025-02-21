package com.mangabible.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangabible.data.repository.MainRepository
import com.mangabible.data.model.MangaResponse
import com.mangabible.ui.MangaVO
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
                val mangaResponse = repository.fetchMangaInfo()
               val data = getMangaData(mangaResponse)
                _state.value = MainState.Success(data?: emptyList())
            } catch (e: Exception) {
                _state.value = MainState.Error("Error: ${e.message}")
            }
        }
    }

    private fun mangaVOList(it: MangaResponse?): List<MangaVO>? {
        return it?.data?.map {
            MangaVO(
                createdAt = it.attributes.createdAt,
                updatedAt = it.attributes.updatedAt,
                synopsis = it.attributes.synopsis,
                title = it.attributes.title,
                status = it.attributes.status,
                coverImage = it.attributes.coverImage,
                ratingRank = it.attributes.ratingRank
            )
        }
    }

    private fun getMangaData(mangaList: List<MangaResponse>): List<MangaVO>? {
        mangaList.map {
            return mangaVOList(it)
        }
        return emptyList()
    }
}