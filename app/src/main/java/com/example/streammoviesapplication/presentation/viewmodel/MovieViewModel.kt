package com.example.streammoviesapplication.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streammoviesapplication.data.model.localData.MovieViewState
import com.example.streammoviesapplication.data.repository.ITrendingMoviesRepository
import com.example.streammoviesapplication.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: ITrendingMoviesRepository
): ViewModel() {

    private var _movieState = MutableStateFlow(MovieViewState())
    val movieState: StateFlow<MovieViewState> = _movieState

    init {
        fetchTrendingMovies()
    }
    private fun fetchTrendingMovies() {
        viewModelScope.launch {
            try {
                val response = repository.fetchTrendingMovies()
                response.collect{result ->
                    when(result) {
                        is Resource.Success -> {
                            _movieState.update { it.copy(isLoading = false, trendingMovies = result.data) }

                        }

                        is Resource.Error -> {
                            _movieState.update { it.copy(isLoading = false, errorMessage = result.message) }

                        }
                        is Resource.Loading -> {
                            _movieState.update { it.copy(isLoading = true) }
                        }
                    }

                }
            } catch (e: Exception) {
                Log.d("CHECK_VIEWMODEL", "Viewmodel result - ${e.message}")

            }

        }
    }
}
