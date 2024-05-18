package com.example.moviesapp.domain.api

import com.example.moviesapp.data.dto.MovieCast
import com.example.moviesapp.domain.models.Movie
import com.example.moviesapp.domain.models.MovieDetails
import com.example.moviesapp.domain.models.MovieListResult
import com.example.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun searchMovie(text: String): Flow<Resource<MovieListResult>>

    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)

    fun getMovieDetails(id: String): Flow<Resource<MovieDetails>>

    fun getMovieCast(movieId: String): Flow<Resource<MovieCast>>
}
