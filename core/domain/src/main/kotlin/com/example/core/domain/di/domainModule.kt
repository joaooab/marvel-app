package com.example.core.domain.di

import com.example.core.domain.usecase.GetComicDetailUseCase
import com.example.core.domain.usecase.GetComicsPagingUseCase
import com.example.core.domain.usecase.impl.GetComicDetailUseCaseImpl
import com.example.core.domain.usecase.impl.GetComicsPagingUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<GetComicsPagingUseCase> { GetComicsPagingUseCaseImpl(get()) }
    factory<GetComicDetailUseCase> { GetComicDetailUseCaseImpl(get()) }
}