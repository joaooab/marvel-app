package com.example.core.domain.usecase.impl

import com.example.core.data.api.MarvelApi
import com.example.core.data.repository.MarvelRepository
import com.example.core.data.repository.impl.MarvelRepositoryImpl
import com.example.core.domain.usecase.ComicDetailFactory
import com.example.core.domain.usecase.ComicFactory
import com.example.core.domain.usecase.GetComicDetailUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class GetComicDetailUseCaseImplTest {
    private lateinit var repository: MarvelRepository

    private lateinit var useCase: GetComicDetailUseCase

    private val comics = ComicFactory().createList()

    private val fakeDetailResponse = ComicDetailFactory().createResponse()

    @Before
    fun setUp() {
        val api = mockk<MarvelApi>()
        coEvery { api.fetchComicDetail(any()) } returns fakeDetailResponse
        repository = MarvelRepositoryImpl(api)
        useCase = GetComicDetailUseCaseImpl(repository)
    }

    @Test
    fun `when call useCase then return expected`() = runTest {
        val result = useCase("1")
        assertNotNull(result)
        assertEquals(result.id,"1")
        assertEquals(result.title,"title")
        assertEquals(result.thumbnail,"https://test.us.jpg")
        assertEquals(result.author,"author")
        assertEquals(result.publicationDate,"2029-12-31")
        assertEquals(result.price,"\$2.99")
        assertEquals(result.description,"description")
    }
}