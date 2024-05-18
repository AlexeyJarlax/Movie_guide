package com.example.moviesapp.data

import android.util.Log
import com.example.moviesapp.data.converters.MovieDbConvertor
import com.example.moviesapp.data.db.AppDatabase
import com.example.moviesapp.data.dto.MovieCast
import com.example.moviesapp.data.dto.MovieCastPerson
import com.example.moviesapp.data.dto.MovieCastRequest
import com.example.moviesapp.data.dto.MovieCastResponse
import com.example.moviesapp.data.dto.MovieDetailsResponse
import com.example.moviesapp.data.dto.MovieDto
import com.example.moviesapp.data.dto.MoviesDetailsRequest
import com.example.moviesapp.data.dto.MoviesSearchRequest
import com.example.moviesapp.data.dto.MoviesSearchResponse
import com.example.moviesapp.data.storage.LocalStorage
import com.example.moviesapp.domain.api.MoviesRepository
import com.example.moviesapp.domain.models.Movie
import com.example.moviesapp.domain.models.MovieDetails
import com.example.moviesapp.domain.models.MovieListResult
import com.example.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val requester: NetworkClient,
    private val localStorage: LocalStorage,
    private val appDatabase: AppDatabase,
    private val movieDbConvertor: MovieDbConvertor
) : MoviesRepository {

    override fun searchMovie(text: String): Flow<Resource<MovieListResult>> = flow {
        val response = requester.doRequest(MoviesSearchRequest(text))
        Log.i("TAG", "searchMovie: " + response.resultCode)

        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }

            200 -> {
                val stored = localStorage.getSavedFavorites()
                with(response as MoviesSearchResponse) {
                    val list = results.map {
                        Movie(
                            it.id,
                            it.resultType,
                            it.image,
                            it.title,
                            it.description,
                            stored.contains(it.id)
                        )
                    }
                    saveMovie(results)
                    emit(Resource.Success(MovieListResult(list, response.resultCode)))
                }
            }

            else -> emit(Resource.Error("Ошибка с сервера"))
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }

    override fun getMovieDetails(id: String): Flow<Resource<MovieDetails>> = flow {
        val response = requester.doRequest(MoviesDetailsRequest(id))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }

            200 -> {
                with(response as MovieDetailsResponse) {
                    emit(
                        Resource.Success(
                            MovieDetails(
                                this.id, title, imDbRating, year,
                                countries, genres, directors, writers, stars, plot
                            )
                        )
                    )
                }
            }

            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }

    override fun getMovieCast(movieId: String): Flow<Resource<MovieCast>> = flow {
        val response = requester.doRequest(MovieCastRequest(movieId))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }

            200 -> {
                with(response as MovieCastResponse) {
                    emit(Resource.Success(
                        data = MovieCast(
                            imdbId = this.imDbId,
                            fullTitle = this.fullTitle,
                            directors = this.directors.items.map { director ->
                                MovieCastPerson(
                                    id = director.id,
                                    name = director.name,
                                    description = director.description,
                                    image = null,
                                )
                            },
                            others = this.others.flatMap { othersResponse ->
                                othersResponse.items.map { person ->
                                    MovieCastPerson(
                                        id = person.id,
                                        name = person.name,
                                        description = "${othersResponse.job} -- ${person.description}",
                                        image = null,
                                    )
                                }
                            },
                            writers = this.writers.items.map { writer ->
                                MovieCastPerson(
                                    id = writer.id,
                                    name = writer.name,
                                    description = writer.description,
                                    image = null,
                                )
                            },
                            actors = this.actors.map { actor ->
                                MovieCastPerson(
                                    id = actor.id,
                                    name = actor.name,
                                    description = actor.asCharacter,
                                    image = actor.image,
                                )
                            }
                        )
                    ))
                }
            }

            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }

    private suspend fun saveMovie(movies: List<MovieDto>) {
        val movieEntities = movies.map { movie -> movieDbConvertor.map(movie) }
        appDatabase.movieDao().insertMovies(movieEntities)
    }
}
