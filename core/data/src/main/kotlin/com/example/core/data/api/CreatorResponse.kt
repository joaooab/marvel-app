package com.example.core.data.api

import com.google.gson.annotations.SerializedName

data class CreatorResponse(
    @SerializedName("items")
    val items: List<CreatorItemResponse>,
)
