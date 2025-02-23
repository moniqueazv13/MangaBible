package com.mangabible.ui.intent

import com.mangabible.ui.MangaVO

sealed class MangaIntent {
    data object FetchManga : MangaIntent()
    data class OpenDetails(val mangaVO: MangaVO) : MangaIntent()
}
