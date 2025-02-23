package com.mangabible

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import com.mangabible.ui.MangaVO
import com.mangabible.ui.screen.MANGA_ITEM
import com.mangabible.ui.screen.MangaDetailsItemScreen
import com.mangabible.ui.theme.AnimeBibleTheme

class DetailsActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mangaVO = intent.getParcelableExtra(MANGA_ITEM, MangaVO::class.java)
        setContent {
            AnimeBibleTheme {
                mangaVO?.let { MangaDetailsItemScreen(it) }
                    ?: Text("Error: Manga details not found.")
            }
        }
    }
}