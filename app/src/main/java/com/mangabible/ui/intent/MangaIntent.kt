package com.mangabible.ui.intent

sealed class MangaIntent {
    data object FetchManga : MangaIntent()
}