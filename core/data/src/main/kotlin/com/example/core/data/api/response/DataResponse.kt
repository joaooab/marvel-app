package com.example.core.data.api.response

import com.google.gson.annotations.SerializedName

data class DataResponse<T>(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("results")
    val results: List<T>
)
