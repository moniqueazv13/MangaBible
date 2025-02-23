package com.mangabible.ui.screen

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.core.content.ContextCompat.startActivity
import com.mangabible.DetailsActivity
import com.mangabible.ui.MangaVO
import com.mangabible.ui.intent.MangaIntent
import com.mangabible.ui.theme.Pink
import com.mangabible.ui.viewmodel.main.MainAction
import com.mangabible.ui.viewmodel.main.MainState
import com.mangabible.ui.viewmodel.main.MainViewModel
import org.koin.androidx.compose.koinViewModel

internal const val MANGA_ITEM = "MANGA_ITEM"

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel(), context: Context) {
    val state by viewModel.state.collectAsState()
    val action by viewModel.action.collectAsState(null)
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
                    CircularProgressIndicator(color = Pink)
                }

                is MainState.Success -> {
                    MangaList((state as MainState.Success).mangaList, onItemClick = { manga ->  viewModel.processIntent(MangaIntent.OpenDetails(manga))})
                }

                is MainState.Error -> {
                    Text((state as MainState.Error).message)
                }
            }

            when (action) {
                is MainAction.RedirectToDetails -> {
                    startActivity(context, Intent(context, DetailsActivity::class.java).apply {
                        putExtra(MANGA_ITEM,  (action as MainAction.RedirectToDetails).mangaVO)
                    }, null)
                }

                else -> {}
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MangaList(mangaList: List<MangaVO>, onItemClick: (MangaVO) -> Unit) {
    Box {
        Text(text = "Animes:", fontSize = 30.sp)

    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(mangaList) { manga ->
            MangaItem(manga = manga, onItemClick)
        }
    }
}

@Composable
fun MangaItem(manga: MangaVO, onItemClick: (MangaVO) -> Unit) {
    Column(
        modifier = Modifier
            .clickable {
                onItemClick.invoke(manga)
            }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = manga.title, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Status: ${manga.status}")
        Spacer(modifier = Modifier.height(8.dp))
    }
}
