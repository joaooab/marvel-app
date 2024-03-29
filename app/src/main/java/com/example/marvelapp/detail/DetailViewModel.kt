package com.example.marvelapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetComicDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val comicId: String,
    private val getDetailUseCase: GetComicDetailUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        load(comicId)
    }

    fun tryAgain() {
        load(comicId)
    }

    private fun load(id: String) {
        viewModelScope.launch {
            val comic = getDetailUseCase(id)
            if (comic == null) _uiState.emit(DetailUiState.Error)
            else _uiState.emit(DetailUiState.Success(comic))
        }
    }
}