package com.example.core.data.api.response

import com.example.core.data.api.ThumbnailResponse
import com.example.core.data.api.toModel
import com.example.core.model.ComicDetail
import com.example.core.model.formatDate
import com.example.core.model.toCurrency
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
    val creators: CreatorResponse,
    @SerializedName("textObjects")
    val textObjects: List<TextObjectsResponse>?
)

fun ComicDetailResponse.toModel() = ComicDetail(
    id = id,
    title = title,
    thumbnail = thumbnail.toModel(),
    author = creators.items.firstOrNull()?.name.orEmpty(),
    publicationDate = dates.firstOrNull()?.date.formatDate(),
    price = prices.firstOrNull()?.price.toCurrency(),
    description = description.takeIf { it.isNotEmpty() }
        ?: textObjects?.firstOrNull()?.text.orEmpty(),
)