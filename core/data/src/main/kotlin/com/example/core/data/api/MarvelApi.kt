package com.example.core.data.api

import com.example.core.data.api.response.ComicDetailResponse
import com.example.core.data.api.response.ComicResponse
import com.example.core.data.api.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("comics")
    suspend fun fetchComics(@QueryMap queries: Map<String, String>): DataWrapperResponse<ComicResponse>

    @GET("comics/{comicId}")
    suspend fun fetchComicDetail(@Path("comicId") id: String): DataWrapperResponse<ComicDetailResponse>
}