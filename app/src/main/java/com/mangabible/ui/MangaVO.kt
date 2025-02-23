package com.mangabible.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MangaVO(
    val createdAt: String,
    val updatedAt: String,
    val synopsis: String,
    val title: String,
    val status: String,
    val coverImage: String,
    val ratingRank: Long
) : Parcelable
