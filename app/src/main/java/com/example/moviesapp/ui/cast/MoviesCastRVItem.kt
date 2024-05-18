package com.example.moviesapp.ui.cast

import com.example.moviesapp.data.dto.MovieCastPerson
import com.example.moviesapp.ui.RVItem

sealed interface MoviesCastRVItem: RVItem {

    data class HeaderItem(
        val headerText: String,
    ) : MoviesCastRVItem

    data class PersonItem(
        val data: MovieCastPerson,
    ) : MoviesCastRVItem

}