package com.example.moviesapp.domain.impl

import com.example.moviesapp.data.dto.MovieCast
import com.example.moviesapp.domain.api.MoviesInteractor
import com.example.moviesapp.domain.api.MoviesRepository
import com.example.moviesapp.domain.models.Movie
import com.example.moviesapp.domain.models.MovieDetails
import com.example.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesInteractorImpl(
    private val moviesRepository: MoviesRepository
) : MoviesInteractor {

    override fun searchMovies(string: String): Flow<Pair<List<Movie>?, String?>> {
        return moviesRepository.searchMovie(string).map { result ->
            when (result) {
                is Resource.Success -> {
                    Pair(result.data?.movies, null)
                }

                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
//        return moviesRepository.searchMovie(string).map { result ->
//            when (result) {
//                is Resource.Success -> {
//                    Pair(result.data, null)
//                }
//
//                is Resource.Error -> {
//                    Pair(null, result.message)
//                }
//            }
//        }

        }
//        executor.execute {
//            when(val resource = moviesRepository.searchMovie(string)) {
//                is Resource.Success -> { consumer.consume(resource.data, null) }
//                is Resource.Error -> { consumer.consume(resource.data, resource.message) }
//            }
//        }
    }

    override fun getMovieDetails(id: String): Flow<Pair<MovieDetails?, String?>> {
        return moviesRepository.getMovieDetails(id).map { result ->
            when (result) {
                is Resource.Success -> {
                    Pair(result.data, null)
                }

                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
//        executor.execute {
//            when (val resource = moviesRepository.getMovieDetails(id)) {
//                is Resource.Success -> {
//                    consumer.consume(resource.data, null)
//                }
//
//                is Resource.Error -> {
//                    consumer.consume(resource.data, resource.message)
//                }
//            }
//        }
    }

    override fun getMoviesCast(movieId: String): Flow<Pair<MovieCast?, String?>> {
        return moviesRepository.getMovieCast(movieId).map { result ->
            when (result) {
                is Resource.Success -> {
                    Pair(result.data, null)
                }

                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }


    override fun addMovieToFavorites(movie: Movie) {
        moviesRepository.addMovieToFavorites(movie)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        moviesRepository.removeMovieFromFavorites(movie)
    }
}
