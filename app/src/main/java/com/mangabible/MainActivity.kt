package com.mangabible

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mangabible.ui.MangaVO
import com.mangabible.ui.intent.MainIntent
import com.mangabible.ui.theme.MyApplicationTheme
import com.mangabible.ui.viewmodel.MainState
import com.mangabible.ui.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme { MainScreen() }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            when (state) {
                is MainState.Idle -> {
                    Button(onClick = { viewModel.processIntent(MainIntent.FetchManga) }) {
                        Text("Fetch Manga")
                    }
                }

                is MainState.Loading -> {
                    CircularProgressIndicator()
                }

                is MainState.Success -> {
                    MangaList((state as MainState.Success).mangaList)
                }

                is MainState.Error -> {
                    Text((state as MainState.Error).message)
                }
            }
        }
    }
}

@Composable
fun LoadImageFromUrl(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Imagem carregada da URL",
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun MangaList(mangaList: List<MangaVO>) {
    mangaList.map {
        Text(text = it.title)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = it.status)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = it.synopsis)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = it.createdAt)
        Text(text = it.updatedAt)
        Text(text = it.ratingRank.toString())
        Spacer(modifier = Modifier.height(8.dp))
        LoadImageFromUrl(imageUrl = it.coverImage)
    }
}