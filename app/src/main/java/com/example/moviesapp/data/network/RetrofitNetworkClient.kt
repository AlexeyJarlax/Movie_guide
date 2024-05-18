package com.example.moviesapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.moviesapp.data.NetworkClient
import com.example.moviesapp.data.dto.MovieCastRequest
import com.example.moviesapp.data.dto.MoviesDetailsRequest
import com.example.moviesapp.data.dto.MoviesSearchRequest
import com.example.moviesapp.data.dto.PersonSearchRequest
import com.example.moviesapp.data.dto.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNetworkClient(private val context: Context) : NetworkClient {
    private val imdbBaseUrl = "https://tv-api.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(imdbBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val imdbService = retrofit.create(IMDbApi::class.java)

    override suspend fun doRequest(dto: Any): Response {
        if (isConnected() == false) {
            return Response().apply { resultCode = -1 }
        }

        if ((dto !is MoviesSearchRequest) && (dto !is MoviesDetailsRequest)
        && (dto !is MovieCastRequest) && (dto !is PersonSearchRequest)) {
            return Response().apply { resultCode = 400 }
        }

        return withContext(Dispatchers.IO) {
            try {
                val response = when (dto) {
                    is MoviesSearchRequest -> imdbService.findMovie(dto.text)
                    is MoviesDetailsRequest -> imdbService.getMovieDetails(dto.id)
                    is PersonSearchRequest -> imdbService.findPerson(dto.name)
                    else -> imdbService.getMovieFullCast((dto as MovieCastRequest).movieId)
                }
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}
