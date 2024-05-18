package com.example.moviesapp.presentation.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.dto.MovieCast
import com.example.moviesapp.domain.api.MoviesInteractor
import com.example.moviesapp.ui.cast.MoviesCastRVItem
import kotlinx.coroutines.launch

class MoviesCastViewModel(
    private val movieId: String,
    private val moviesInteractor: MoviesInteractor,
) : ViewModel() {

    private val stateLiveData = MutableLiveData<MoviesCastState>()
    fun observeState(): LiveData<MoviesCastState> = stateLiveData

    init {
        stateLiveData.postValue(MoviesCastState.Loading)

        viewModelScope.launch {
            moviesInteractor
                .getMoviesCast(movieId)
                .collect { pair ->
                    if (pair.first != null) {
                        stateLiveData.postValue(castToUiStateContent(pair.first!!))
                    } else {
                        stateLiveData.postValue(
                            MoviesCastState.Error(
                                pair.second ?: "Unknown error"
                            )
                        )
                    }

                }
        }
    }

    private fun castToUiStateContent(cast: MovieCast): MoviesCastState {
        // Строим список элементов RecyclerView
        val items = buildList<MoviesCastRVItem> {
            // Если есть хотя бы один режиссёр, добавим заголовок
            if (cast.directors.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Directors")
                this += cast.directors.map { MoviesCastRVItem.PersonItem(it) }
            }

            // Если есть хотя бы один сценарист, добавим заголовок
            if (cast.writers.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Writers")
                this += cast.writers.map { MoviesCastRVItem.PersonItem(it) }
            }

            // Если есть хотя бы один актёр, добавим заголовок
            if (cast.actors.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Actors")
                this += cast.actors.map { MoviesCastRVItem.PersonItem(it) }
            }

            // Если есть хотя бы один дополнительный участник, добавим заголовок
            if (cast.others.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Others")
                this += cast.others.map { MoviesCastRVItem.PersonItem(it) }
            }
        }


        return MoviesCastState.Content(
            fullTitle = cast.fullTitle,
            items = items
        )
    }
}
