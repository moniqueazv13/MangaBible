package com.mangabible.ui.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangabible.data.model.response.Data
import com.mangabible.data.repository.MangaRepositoryImpl
import com.mangabible.ui.MangaVO
import com.mangabible.ui.intent.MangaIntent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MangaRepositoryImpl) : ViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState> = _state

    private val _action = MutableSharedFlow<MainAction>()
    val action: SharedFlow<MainAction> = _action

    fun processIntent(intent: MangaIntent) {
        when (intent) {
            is MangaIntent.FetchManga -> fetchManga()
            is MangaIntent.OpenDetails -> openDetails(intent.mangaVO)
        }
    }

    private fun openDetails(mangaVO: MangaVO) {
        viewModelScope.launch {
            _action.emit(MainAction.RedirectToDetails(mangaVO))
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

    private fun mapMangaResponsesToVOs(mangaResponses: List<Data>) = mangaResponses.map {
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