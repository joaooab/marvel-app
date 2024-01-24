package com.example.core.data.api

import com.example.core.model.Comic
import com.google.gson.annotations.SerializedName

data class ComicResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse,
)

fun ComicResponse.toModel() = Comic(
    id = id,
    title = title,
    thumbnail = thumbnail.toModel()
)