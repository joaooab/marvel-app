package com.example.core.data.repository.impl

import com.example.core.data.api.MarvelApi
import com.example.core.data.api.toModel
import com.example.core.data.repository.MarvelRepository
import com.example.core.data.source.ComicsPagingSource

class MarvelRepositoryImpl(private val api: MarvelApi) : MarvelRepository {

    override fun getComics() = ComicsPagingSource(api)

    override suspend fun getComicDetail(id: String) = api
        .fetchComicDetail(id)
        .data
        .results
        .firstOrNull()
        ?.toModel()
}