package ru.yandex.practicum.moviessearch.di

import org.koin.dsl.module
import ru.yandex.practicum.moviessearch.domain.api.MoviesInteractor
import ru.yandex.practicum.moviessearch.domain.impl.MoviesInteractorImpl

val interactorModule = module {

    single<MoviesInteractor> {
        MoviesInteractorImpl(get())
    }

}