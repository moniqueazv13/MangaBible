package com.mangabible.ui.viewmodel.main

import com.mangabible.ui.MangaVO

sealed class MainAction {
    data class RedirectToDetails(val mangaVO: MangaVO): MainAction()
}