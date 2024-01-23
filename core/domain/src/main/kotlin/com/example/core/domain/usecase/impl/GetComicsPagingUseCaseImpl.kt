package com.example.core.domain.usecase.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.repository.MarvelRepository
import com.example.core.domain.usecase.GetComicsPagingUseCase
import com.example.core.model.Comic
import kotlinx.coroutines.flow.Flow

class GetComicsPagingUseCaseImpl(
    private val repository: MarvelRepository
) : GetComicsPagingUseCase {

    override fun invoke(pagingConfig: PagingConfig): Flow<PagingData<Comic>> {
        val pagingSource = repository.getComics()
        return Pager(config = pagingConfig) {
            pagingSource
        }.flow
    }
}