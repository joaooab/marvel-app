package com.example.core.data.api

import com.example.core.model.ComicDetail
import com.google.gson.annotations.SerializedName

data class ComicDetailResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse,
    @SerializedName("description")
    val description: String,
    @SerializedName("dates")
    val dates: List<DateResponse>,
    @SerializedName("prices")
    val prices: List<PriceResponse>,
    @SerializedName("creators")
    val creators: CreatorResponse
)

fun ComicDetailResponse.toModel() = ComicDetail(
    id = id,
    title = title,
    thumbnail = thumbnail.toModel(),
    author = creators.items.firstOrNull()?.name.orEmpty(),
    publicationDate = dates.firstOrNull()?.date.orEmpty(),
    price = prices.firstOrNull()?.price.orEmpty(),
    description = description,
)