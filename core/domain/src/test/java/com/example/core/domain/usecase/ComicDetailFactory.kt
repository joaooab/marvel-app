package com.example.core.domain.usecase

import com.example.core.data.api.ThumbnailResponse
import com.example.core.data.api.response.ComicDetailResponse
import com.example.core.data.api.response.CreatorItemResponse
import com.example.core.data.api.response.CreatorResponse
import com.example.core.data.api.response.DataResponse
import com.example.core.data.api.response.DataWrapperResponse
import com.example.core.data.api.response.DateResponse
import com.example.core.data.api.response.PriceResponse

class ComicDetailFactory {

    fun createResponse() = DataWrapperResponse(
        copyRight = "",
        data = DataResponse(
            offset = 0,
            total = 0,
            results = listOf(
                ComicDetailResponse(
                    id = "1",
                    title = "title",
                    thumbnail = ThumbnailResponse(path = "http://test.us", extension = "jpg"),
                    description = "description",
                    dates = listOf(
                        DateResponse(
                            type = "onsaleDate",
                            date = "2029-12-31T00:00:00-0500"
                        )
                    ),
                    prices = listOf(
                        PriceResponse(
                            type = "printPrice",
                            price = "2.99"
                        )
                    ),
                    creators = CreatorResponse(
                        items = listOf(
                            CreatorItemResponse(
                                name = "author"
                            )
                        )
                    ),
                    textObjects = listOf()
                )
            )
        )
    )
}