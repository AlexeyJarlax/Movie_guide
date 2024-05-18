package com.example.moviesapp.data

import com.example.moviesapp.data.dto.PersonSearchRequest
import com.example.moviesapp.data.dto.PersonsSearchResponse
import com.example.moviesapp.domain.api.NamesRepository
import com.example.moviesapp.domain.models.Person
import com.example.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NamesRepositoryImpl(private val networkClient: NetworkClient) : NamesRepository {

    override fun searchNames(expression: String): Flow<Resource<List<Person>>> = flow {
        val response = networkClient.doRequest(PersonSearchRequest(expression))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                with(response as PersonsSearchResponse) {
                    val data = results.map{
                        Person(id = it.id,
                            name = it.title,
                            description = it.description,
                            photoUrl = it.image)
                    }
                    emit(Resource.Success(data))
                }
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }
}