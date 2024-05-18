package com.example.moviesapp.domain.db

import com.example.moviesapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun historyMovies(): Flow<List<Movie>>
}