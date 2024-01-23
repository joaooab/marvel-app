package com.example.marvelapp.di

import com.example.marvelapp.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModule = module {
    viewModelOf(::ListViewModel)
}