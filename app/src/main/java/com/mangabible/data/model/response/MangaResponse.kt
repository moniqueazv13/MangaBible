package com.mangabible.data.model.response
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val id: String,
    val attributes: Attributes
)

@JsonClass(generateAdapter = true)
data class Attributes(
    val createdAt: String,
    val updatedAt: String,
    val synopsis: String,
    val title: String,
    val ratingRank: Long,
    val status: String,
    val coverImage: String
)