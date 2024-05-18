package com.example.moviesapp.domain.impl

import com.example.moviesapp.domain.api.HistoryInteractor
import com.example.moviesapp.domain.db.HistoryRepository
import com.example.moviesapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

class HistoryInteractorImpl(private val historyRepository: HistoryRepository): HistoryInteractor {
    override fun historyMovies(): Flow<List<Movie>> {
        return historyRepository.historyMovies()
    }
}