package com.mangabible.ui.intent

sealed class MainIntent {
    data object FetchManga : MainIntent()
}