package com.example.moviesapp

import android.app.Application
import com.example.moviesapp.di.repositoryModule
import com.example.moviesapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Метод специального класса, переданного как this, для добавления контекста в граф
            androidContext(this@MoviesApplication)
            // Передаём все модули, чтобы их содержимое было передано в граф
            modules(repositoryModule, viewModelModule)
        }
    }
}
