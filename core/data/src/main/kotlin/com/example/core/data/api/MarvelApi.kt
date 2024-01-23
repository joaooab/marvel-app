package com.example.core.data.api

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("comics")
    suspend fun fetchComics(@QueryMap queries: Map<String, String>): DataWrapperResponse
}