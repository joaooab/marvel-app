package com.example.core.data.api

import com.example.core.model.Comic
import com.google.gson.annotations.SerializedName

data class ComicResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)

fun ComicResponse.toModel() = Comic (
    title = title,
    thumbnail = thumbnail.toModel()
)