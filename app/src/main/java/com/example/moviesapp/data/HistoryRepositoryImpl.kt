package com.example.moviesapp.data

import com.example.moviesapp.data.converters.MovieDbConvertor
import com.example.moviesapp.data.db.AppDatabase
import com.example.moviesapp.data.db.entity.MovieEntity
import com.example.moviesapp.domain.db.HistoryRepository
import com.example.moviesapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HistoryRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val movieDbConvertor: MovieDbConvertor
) : HistoryRepository {
    override fun historyMovies(): Flow<List<Movie>> = flow {
        val movies = appDatabase.movieDao().getMovies()
        emit(convertFromMovieEntity(movies))
    }

    private fun convertFromMovieEntity(movies: List<MovieEntity>): List<Movie> {
        return movies.map { movie -> movieDbConvertor.map(movie) }
    }
}