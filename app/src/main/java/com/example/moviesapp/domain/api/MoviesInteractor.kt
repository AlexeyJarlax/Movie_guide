package com.example.moviesapp.domain.api

import com.example.moviesapp.data.dto.MovieCast
import com.example.moviesapp.domain.models.Movie
import com.example.moviesapp.domain.models.MovieDetails
import com.example.moviesapp.domain.models.MovieListResult
import kotlinx.coroutines.flow.Flow

interface MoviesInteractor {
    fun searchMovies(string: String): Flow<Pair<List<Movie>?, String?>>

    fun addMovieToFavorites(movie: Movie)

    fun removeMovieFromFavorites(movie: Movie)

    fun getMovieDetails(id: String): Flow<Pair<MovieDetails?, String?>>

    fun getMoviesCast(movieId: String): Flow<Pair<MovieCast?, String?>>
}
