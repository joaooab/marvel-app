package com.example.core.data.repository.impl

import com.example.core.data.api.MarvelApi
import com.example.core.data.api.response.toModel
import com.example.core.data.api.safeApiCallOrNull
import com.example.core.data.repository.MarvelRepository
import com.example.core.data.source.ComicsPagingSource

class MarvelRepositoryImpl(private val api: MarvelApi) : MarvelRepository {

    override fun getComics(query: String) = ComicsPagingSource(
        query = query,
        fetchComics = { queries ->
            safeApiCallOrNull {
                api.fetchComics(queries)
            }
        }
    )

    override suspend fun getComicDetail(id: String) = safeApiCallOrNull {
        api.fetchComicDetail(id)
    }?.data
        ?.results
        ?.firstOrNull()
        ?.toModel()
}