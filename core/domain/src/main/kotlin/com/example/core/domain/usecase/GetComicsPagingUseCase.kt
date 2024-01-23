package com.example.core.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.model.Comic
import kotlinx.coroutines.flow.Flow

interface GetComicsPagingUseCase {

    operator fun invoke(pagingConfig: PagingConfig): Flow<PagingData<Comic>>
}