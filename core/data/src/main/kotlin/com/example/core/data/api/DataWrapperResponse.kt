package com.example.core.data.api

import com.google.gson.annotations.SerializedName

data class DataWrapperResponse<T>(
    @SerializedName("copyright")
    val copyRight: String,
    @SerializedName("data")
    val data: DataResponse<T>
)
