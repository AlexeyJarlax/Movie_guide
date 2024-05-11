package ru.yandex.practicum.moviessearch.data

import ru.yandex.practicum.moviessearch.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}