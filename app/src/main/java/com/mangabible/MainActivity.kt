package com.mangabible

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mangabible.ui.DateTimeUtils
import com.mangabible.ui.MangaVO
import com.mangabible.ui.intent.MangaIntent
import com.mangabible.ui.theme.MyApplicationTheme
import com.mangabible.ui.viewmodel.main.MainState
import com.mangabible.ui.viewmodel.main.MainViewModel
import org.koin.androidx.compose.koinViewModel

private const val EMPTY_STRING = ""

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme { MainScreen() }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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
                    viewModel.processIntent(MangaIntent.FetchManga)
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
        contentDescription = EMPTY_STRING,
        modifier = Modifier.size(300.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MangaList(mangaList: List<MangaVO>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(mangaList) { manga ->
            MangaItem(manga = manga)
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MangaItem(manga: MangaVO) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Title: ${manga.title}", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Status: ${manga.status}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Lore: ${manga.synopsis}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Create date: ${DateTimeUtils.convertServerTimeToLocal(manga.createdAt)}")
        Text(text = "Last update: ${DateTimeUtils.convertServerTimeToLocal(manga.updatedAt)}")
        Text(text = "Rating: ${manga.ratingRank}")
        Spacer(modifier = Modifier.height(8.dp))
        LoadImageFromUrl(imageUrl = manga.coverImage)
        Spacer(modifier = Modifier.height(8.dp))
    }
}