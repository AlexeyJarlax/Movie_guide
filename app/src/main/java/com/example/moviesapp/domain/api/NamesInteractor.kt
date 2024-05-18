package com.example.moviesapp.domain.api

import com.example.moviesapp.domain.models.Person
import kotlinx.coroutines.flow.Flow


interface NamesInteractor {
    fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>>
}