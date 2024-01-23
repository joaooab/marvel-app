package com.example.core.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.data.api.MarvelApi
import com.example.core.data.api.toModel
import com.example.core.model.Comic

class ComicsPagingSource(
    private val api: MarvelApi,
): PagingSource<Int, Comic>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comic> {
        return try {
            val offset = params.key ?: 0
            val queries = hashMapOf(
                "offset" to offset.toString()
            )

            val response = api.fetchComics(queries)

            val responseOffset = response.data.offset
            val total = response.data.total

            LoadResult.Page(
                data = response.data.results.map { it.toModel() },
                prevKey = null,
                nextKey = if (responseOffset < total) {
                    responseOffset + LIMIT
                } else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Comic>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}