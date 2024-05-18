package com.example.moviesapp.presentation.history

import com.example.moviesapp.domain.models.Movie

sealed interface HistoryState {
    data object Loading : HistoryState

    data class Content(
        val movies: List<Movie>
    ) : HistoryState

    data class Empty(
        val message: String
    ) : HistoryState
}