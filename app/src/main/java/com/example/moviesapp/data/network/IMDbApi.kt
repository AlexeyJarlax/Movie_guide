package com.example.moviesapp.data.network

import com.example.moviesapp.data.dto.MovieCastResponse
import com.example.moviesapp.data.dto.MovieDetailsResponse
import com.example.moviesapp.data.dto.MoviesSearchResponse
import com.example.moviesapp.data.dto.PersonsSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApi {
    @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
    suspend fun findMovie(@Path("expression") expression: String): MoviesSearchResponse

    @GET("/en/API/Title/k_zcuw1ytf/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: String): MovieDetailsResponse

    @GET("/en/API/FullCast/k_zcuw1ytf/{movie_id}")
    suspend fun getMovieFullCast(@Path("movie_id") movieId: String): MovieCastResponse

    @GET("/en/API/SearchName/k_zcuw1ytf/{expression}")
    suspend fun findPerson(@Path("expression") name: String): PersonsSearchResponse

}
