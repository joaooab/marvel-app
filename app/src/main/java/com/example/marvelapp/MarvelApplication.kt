package com.example.marvelapp

import android.app.Application
import com.example.core.data.di.dataModule
import com.example.core.domain.di.domainModule
import com.example.marvelapp.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvelApplication)
            modules(dataModule, domainModule, viewModule)
        }
    }
}