package com.example.moviesapp.presentation.cast

import com.example.moviesapp.data.dto.MovieCast
import com.example.moviesapp.data.dto.MovieCastResponse
import com.example.moviesapp.ui.RVItem
import com.example.moviesapp.ui.cast.MoviesCastRVItem

sealed interface MoviesCastState {

    object Loading : MoviesCastState

    data class Content(
        val fullTitle: String,
        val items: List<RVItem>,
    ) : MoviesCastState

    data class Error(
        val message: String,
    ) : MoviesCastState

}
