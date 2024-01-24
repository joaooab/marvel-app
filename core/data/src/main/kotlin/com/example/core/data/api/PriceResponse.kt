package com.example.core.data.api

import com.google.gson.annotations.SerializedName

data class PriceResponse(
    @SerializedName("type")
    val type: String,
    @SerializedName("price")
    val price: String,
)
