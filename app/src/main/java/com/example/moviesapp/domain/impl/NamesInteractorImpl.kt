package com.example.moviesapp.domain.impl

import com.example.moviesapp.domain.api.NamesInteractor
import com.example.moviesapp.domain.api.NamesRepository
import com.example.moviesapp.domain.models.Person
import com.example.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executors

class NamesInteractorImpl(private val namesRepository: NamesRepository): NamesInteractor {

    override fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>> {
        return namesRepository.searchNames(expression).map { result ->
            when(result) {
                is Resource.Success -> {
                    Pair(result.data, null)
                }
                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }
}