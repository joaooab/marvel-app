package com.example.marvelapp.detail

import com.example.core.model.ComicDetail

sealed interface DetailUiState {
    data object Loading : DetailUiState
    data class Success(val comic: ComicDetail) : DetailUiState
    data object Error : DetailUiState
}