package com.mangabible.ui

data class MangaVO(
    val createdAt: String,
    val updatedAt: String,
    val synopsis: String,
    val title: String,
    val ratingRank: Long,
    val status: String,
    val coverImage: String
)
