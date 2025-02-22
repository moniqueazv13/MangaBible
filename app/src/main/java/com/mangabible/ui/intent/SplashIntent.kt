package com.mangabible.ui.intent

sealed class SplashIntent {
    data object CheckLoginStatus : SplashIntent()
}