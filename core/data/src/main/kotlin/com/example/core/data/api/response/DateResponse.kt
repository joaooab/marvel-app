package com.example.core.data.api.response

import com.google.gson.annotations.SerializedName

data class DateResponse(
    @SerializedName("type")
    val type: String,
    @SerializedName("date")
    val date: String,
)
