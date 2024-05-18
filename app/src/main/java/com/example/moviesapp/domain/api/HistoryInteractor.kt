package com.example.moviesapp.domain.api

import com.example.moviesapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface HistoryInteractor {
    fun historyMovies(): Flow<List<Movie>>
}