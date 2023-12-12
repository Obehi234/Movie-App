package com.example.streammoviesapplication.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.streammoviesapplication.data.model.localData.MovieDetailsEntity
import com.example.streammoviesapplication.data.model.localData.MovieViewState
import com.example.streammoviesapplication.data.repository.IMoviesDetailsRepository
import com.example.streammoviesapplication.data.repository.ITrendingMoviesRepository
import com.example.streammoviesapplication.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val trendingMoviesRepository: ITrendingMoviesRepository,
    private val detailsRepository: IMoviesDetailsRepository
) : ViewModel() {

    private var _movieState = MutableStateFlow(MovieViewState())
    val movieState: StateFlow<MovieViewState> = _movieState

    private val _movieDetails = MutableLiveData<Resource<MovieDetailsEntity>>()
    val movieDetails: LiveData<Resource<MovieDetailsEntity>> = _movieDetails

    init {
        fetchTrendingMovies()
    }

    private fun fetchTrendingMovies() {
        viewModelScope.launch {
            try {
                val response = trendingMoviesRepository.fetchTrendingMovies()
                response.collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _movieState.update {
                                it.copy(
                                    isLoading = false,
                                    trendingMovies = result.data
                                )
                            }
                        }

                        is Resource.Error -> {
                            _movieState.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = result.message
                                )
                            }
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

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                _movieDetails.value = Resource.Loading()

                detailsRepository.fetchMovieDetails(movieId).collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _movieDetails.value = result
                            Log.d("CHECK_VM", "Details Result - ${result.data}")
                        }

                        is Resource.Error -> {
                            val errorMessage = result.message ?: "Unknown error"
                            Log.e("CHECK_VM", "Details Error - $errorMessage")
                            _movieDetails.value = Resource.Error(errorMessage, null)
                        }

                        is Resource.Loading -> {
                            // You can handle loading states if needed
                            Log.d("CHECK_VM", "Details Loading")
                        }
                    }
                }
            } catch (e: Exception) {
                _movieDetails.value = Resource.Error(e.message ?: "An error occurred", null)
            }
        }
    }
}

