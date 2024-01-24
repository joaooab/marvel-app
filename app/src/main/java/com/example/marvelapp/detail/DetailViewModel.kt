package com.example.marvelapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.repository.MarvelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MarvelRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun load(id: String) {
        viewModelScope.launch {
            val comic = repository.getComicDetail(id)
            if (comic == null) _uiState.emit(DetailUiState.Error)
            else _uiState.emit(DetailUiState.Success(comic))
        }
    }
}