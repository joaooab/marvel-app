package com.example.core.data.di

import com.example.core.data.api.MarvelApi
import com.example.core.data.api.NetworkConfig
import com.example.core.data.repository.MarvelRepository
import com.example.core.data.repository.impl.MarvelRepositoryImpl
import com.example.core.data.source.ComicsPagingSource
import org.koin.dsl.module

val dataModule = module {
    single { NetworkConfig.create<MarvelApi>() }
    single<MarvelRepository> { MarvelRepositoryImpl(get()) }
    factory { ComicsPagingSource(get()) }
}