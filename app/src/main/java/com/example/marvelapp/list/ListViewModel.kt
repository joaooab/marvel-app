package com.example.marvelapp.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.core.domain.usecase.GetComicsPagingUseCase

class ListViewModel(
    private val getComicsPagingUseCase: GetComicsPagingUseCase
) : ViewModel() {

    fun comicPagingData() = getComicsPagingUseCase(
        pagingConfig = PagingConfig(pageSize = 20)
    ).cachedIn(viewModelScope)
}