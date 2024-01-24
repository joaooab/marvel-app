package com.example.core.domain.usecase.impl

import androidx.paging.PagingConfig
import com.example.core.data.repository.MarvelRepository
import com.example.core.domain.PagingSourceFactory
import com.example.core.domain.usecase.ComicFactory
import com.example.core.domain.usecase.GetComicsPagingUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetComicsPagingUseCaseImplTest {

    private lateinit var repository: MarvelRepository

    private lateinit var useCase: GetComicsPagingUseCase

    private val comics = ComicFactory().createList()

    private val fakePagingSource = PagingSourceFactory().create(comics)

    @Before
    fun setUp() {
        repository = mockk()
        coEvery { repository.getComics(any()) } returns fakePagingSource
        useCase = GetComicsPagingUseCaseImpl(repository)
    }

    @Test
    fun `when call useCase then getComics should be called`() = runTest {
        useCase.invoke(
            query = "",
            pagingConfig = PagingConfig(20)
        ).first()

        verify(exactly = 1) { repository.getComics("") }
    }

    @Test
    fun `when call useCase with query then getComics should be called with query `() = runTest {
        val query = "test"
        useCase.invoke(
            query = query,
            pagingConfig = PagingConfig(20)
        ).first()

        verify(exactly = 1) { repository.getComics(query) }
    }
}