package com.example.core.domain.usecase.impl

import com.example.core.data.repository.MarvelRepository
import com.example.core.domain.usecase.GetComicDetailUseCase
import com.example.core.model.ComicDetail

internal class GetComicDetailUseCaseImpl(
    private val repository: MarvelRepository
) : GetComicDetailUseCase {

    override suspend fun invoke(id: String): ComicDetail? {
        return repository.getComicDetail(id)
    }
}