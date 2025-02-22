package com.mangabible.ui.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangabible.data.repository.MangaRepositoryImpl
import com.mangabible.data.model.MangaResponse
import com.mangabible.ui.MangaVO
import com.mangabible.ui.intent.MangaIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MangaRepositoryImpl) : ViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState> = _state

    fun processIntent(intent: MangaIntent) {
        when (intent) {
            is MangaIntent.FetchManga -> fetchManga()
        }
    }

    private fun fetchManga() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            try {
                val mangaResponse = repository.fetchMangaInfo()
               val data = mapMangaResponsesToVOs(mangaResponse)
                _state.value = MainState.Success(data)
            } catch (e: Exception) {
                _state.value = MainState.Error("Error: ${e.message}")
            }
        }
    }

    private fun mapMangaResponsesToVOs(mangaResponses: List<MangaResponse>): List<MangaVO> {
        val mangaVOs = mutableListOf<MangaVO>()
        for (response in mangaResponses) {
            for (mangaData in response.data) {
                val mangaVO = MangaVO(
                    createdAt = mangaData.attributes.createdAt,
                    updatedAt = mangaData.attributes.updatedAt,
                    synopsis = mangaData.attributes.synopsis,
                    title = mangaData.attributes.title,
                    status = mangaData.attributes.status,
                    coverImage = mangaData.attributes.coverImage,
                    ratingRank = mangaData.attributes.ratingRank
                )
                mangaVOs.add(mangaVO)
            }
        }
        return mangaVOs
    }
}