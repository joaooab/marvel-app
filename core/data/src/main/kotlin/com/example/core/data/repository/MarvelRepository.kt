package com.example.core.data.repository

import androidx.paging.PagingSource
import com.example.core.model.Comic
import com.example.core.model.ComicDetail

interface MarvelRepository {

    fun getComics(): PagingSource<Int, Comic>

    suspend fun getComicDetail(id: String): ComicDetail?
}