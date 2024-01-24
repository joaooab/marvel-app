package com.example.core.data.api.response

import com.google.gson.annotations.SerializedName

data class CreatorResponse(
    @SerializedName("items")
    val items: List<CreatorItemResponse>,
)
