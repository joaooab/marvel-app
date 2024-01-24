package com.example.core.data.api.response

import com.google.gson.annotations.SerializedName

data class TextObjectsResponse(
    @SerializedName("text")
    val text: String,
)
