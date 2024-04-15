package ru.yandex.practicum.moviessearch.di

import org.koin.dsl.module
import ru.yandex.practicum.moviessearch.data.MoviesRepositoryImpl
import ru.yandex.practicum.moviessearch.data.NamesRepositoryImpl
import ru.yandex.practicum.moviessearch.data.converters.MovieCastConverter
import ru.yandex.practicum.moviessearch.domain.api.MoviesRepository
import ru.yandex.practicum.moviessearch.domain.api.NamesRepository

val repositoryModule = module {

    factory { MovieCastConverter() }

    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get())
    }

    single<NamesRepository> {
        NamesRepositoryImpl(get())
    }

}