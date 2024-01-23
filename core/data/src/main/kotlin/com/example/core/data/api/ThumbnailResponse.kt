package com.example.core.data.api

import com.google.gson.annotations.SerializedName

data class ThumbnailResponse(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
)

fun ThumbnailResponse.toModel() = "${this.path}.${this.extension}"
    .replace("http", "https")