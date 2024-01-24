package com.example.core.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.model.Comic

class PagingSourceFactory {

    fun create(comics: List<Comic>) = object : PagingSource<Int, Comic>() {
        override fun getRefreshKey(state: PagingState<Int, Comic>) = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comic> {
            return LoadResult.Page(
                data = comics,
                prevKey = null,
                nextKey = 20
            )
        }
    }
}