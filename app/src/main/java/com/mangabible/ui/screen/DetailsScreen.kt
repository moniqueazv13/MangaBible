package com.mangabible.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mangabible.ui.DateTimeUtils
import com.mangabible.ui.MangaVO

const val EMPTY_STRING = ""

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
fun MangaDetailsItemScreen(manga: MangaVO) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Title: ${manga.title}", fontSize = 20.sp)
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