package com.example.moviesapp.domain.api

import com.example.moviesapp.domain.models.Person
import com.example.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface NamesRepository {
    fun searchNames(expression: String): Flow<Resource<List<Person>>>
}