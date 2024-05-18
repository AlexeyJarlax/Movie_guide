package com.example.moviesapp.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.api.MoviesInteractor
import kotlinx.coroutines.launch

class AboutViewModel(
    private val movieId: String,
    private val moviesInteractor: MoviesInteractor,
) : ViewModel() {

    private val stateLiveData = MutableLiveData<AboutState>()
    fun observeState(): LiveData<AboutState> = stateLiveData

    init {
        viewModelScope.launch {
            moviesInteractor
                .getMovieDetails(movieId)
                .collect { pair ->
                    if (pair.first != null) {
                        stateLiveData.postValue(AboutState.Content(pair.first!!))
                    } else {
                        stateLiveData.postValue(AboutState.Error(pair.second ?: "Unknown error"))
                    }

                }
        }
    }
}