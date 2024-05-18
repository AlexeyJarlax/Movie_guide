package com.example.moviesapp.data

import com.example.moviesapp.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
//    suspend fun doRequestSuspend(dto: Any): Response
}
