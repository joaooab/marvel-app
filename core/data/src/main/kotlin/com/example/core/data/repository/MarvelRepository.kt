package com.example.core.data.repository

import androidx.paging.PagingSource
import com.example.core.model.Comic

interface MarvelRepository {

   fun getComics(): PagingSource<Int, Comic>
}