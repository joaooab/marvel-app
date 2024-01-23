package com.example.core.data.repository.impl

import com.example.core.data.api.MarvelApi
import com.example.core.data.repository.MarvelRepository
import com.example.core.data.source.ComicsPagingSource

class MarvelRepositoryImpl(private val api: MarvelApi) : MarvelRepository {

    override fun getComics() = ComicsPagingSource(api)
}