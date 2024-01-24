package com.example.marvelapp.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.core.domain.usecase.GetComicsPagingUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class ListViewModel(
    private val getComicsPagingUseCase: GetComicsPagingUseCase,
) : ViewModel() {

    private val _query = MutableStateFlow<String>("")

    val pagingDataFlow = _query.debounce(300)
        .flatMapLatest { query ->
            getComicsPagingUseCase(
                query = query,
                pagingConfig = PagingConfig(pageSize = 20)
            )
        }.cachedIn(viewModelScope)

    fun search(newText: String?) {
        viewModelScope.launch {
            _query.update {
                newText.orEmpty()
            }
        }
    }
}