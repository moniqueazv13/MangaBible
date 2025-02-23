package com.mangabible

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.mangabible.ui.MangaVO
import com.mangabible.ui.screen.MANGA_ITEM
import com.mangabible.ui.screen.MangaDetailsItemScreen

class DetailsActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mangaVO = intent.getParcelableExtra(MANGA_ITEM, MangaVO::class.java)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    mangaVO?.let { MangaDetailsItemScreen(it) }
                        ?: Text("Error: Manga details not found.")
                }
            }
        }
    }
}