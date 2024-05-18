package com.example.moviesapp.ui.persons.models

import com.example.moviesapp.data.dto.PersonDto
import com.example.moviesapp.domain.models.Person

sealed interface NamesState {

    object Loading : NamesState

    data class Content(
        val persons: List<Person>
    ) : NamesState

    data class Error(
        val message: String
    ) : NamesState

    data class Empty(
        val message: String
    ) : NamesState

}