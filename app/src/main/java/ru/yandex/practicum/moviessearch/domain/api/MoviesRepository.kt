package ru.yandex.practicum.moviessearch.domain.api

import ru.yandex.practicum.moviessearch.domain.models.Movie
import ru.yandex.practicum.moviessearch.domain.models.MovieCast
import ru.yandex.practicum.moviessearch.domain.models.MovieDetails
import ru.yandex.practicum.moviessearch.util.Resource

interface MoviesRepository {
    suspend fun searchMovies(expression: String): Resource<List<Movie>>
    suspend fun getMovieDetails(movieId: String): Resource<MovieDetails>
    suspend fun getMovieCast(movieId: String): Resource<MovieCast>
}
