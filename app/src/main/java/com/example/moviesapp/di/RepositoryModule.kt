package com.example.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.example.moviesapp.data.HistoryRepositoryImpl
import com.example.moviesapp.data.MoviesRepositoryImpl
import com.example.moviesapp.data.NamesRepositoryImpl
import com.example.moviesapp.data.NetworkClient
import com.example.moviesapp.data.converters.MovieDbConvertor
import com.example.moviesapp.data.db.AppDatabase
import com.example.moviesapp.data.network.RetrofitNetworkClient
import com.example.moviesapp.data.storage.LocalStorage
import com.example.moviesapp.domain.api.HistoryInteractor
import com.example.moviesapp.domain.api.MoviesInteractor
import com.example.moviesapp.domain.api.MoviesRepository
import com.example.moviesapp.domain.api.NamesInteractor
import com.example.moviesapp.domain.api.NamesRepository
import com.example.moviesapp.domain.db.HistoryRepository
import com.example.moviesapp.domain.impl.HistoryInteractorImpl
import com.example.moviesapp.domain.impl.MoviesInteractorImpl
import com.example.moviesapp.domain.impl.NamesInteractorImpl
import com.example.moviesapp.presentation.about.AboutViewModel
import com.example.moviesapp.presentation.cast.MoviesCastViewModel
import com.example.moviesapp.presentation.history.HistoryViewModel
import com.example.moviesapp.presentation.movies.MoviesSearchViewModel
import com.example.moviesapp.presentation.persons.NamesViewModel
import com.example.moviesapp.presentation.poster.PosterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {

    single<MoviesInteractor> {
        MoviesInteractorImpl(get())
    }

    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get(), get(), get())
    }

    single<NamesInteractor> {
        NamesInteractorImpl(get())
    }

    single<NamesRepository> {
        NamesRepositoryImpl(get())
    }

    single<NetworkClient> {
        RetrofitNetworkClient(androidContext())
    }

    single<LocalStorage> {
        LocalStorage(androidContext().getSharedPreferences("local_storage", Context.MODE_PRIVATE))
    }

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db")
            .build()
    }

    factory { MovieDbConvertor() }

    single<HistoryRepository> {
        HistoryRepositoryImpl(get(), get())
    }

    single<HistoryInteractor> {
        HistoryInteractorImpl(get())
    }
}

val viewModelModule = module {
    viewModel {
        MoviesSearchViewModel(get())
    }

    viewModel { (movieId: String) ->
        AboutViewModel(movieId, get())
    }

    viewModel { (posterUrl: String) ->
        PosterViewModel(posterUrl)
    }

    viewModel { (movieId: String) ->
        MoviesCastViewModel(movieId, get())
    }

    viewModel {
        NamesViewModel(androidContext(), get())
    }

    viewModel {
        HistoryViewModel(androidContext(), get())
    }
}