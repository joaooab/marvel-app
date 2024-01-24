package com.example.core.domain.usecase

import com.example.core.model.ComicDetail

interface GetComicDetailUseCase {

    suspend operator fun invoke(id: String): ComicDetail?
}