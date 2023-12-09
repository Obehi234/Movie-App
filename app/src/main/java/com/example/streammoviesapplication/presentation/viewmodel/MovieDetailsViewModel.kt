package com.example.streammoviesapplication.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streammoviesapplication.data.model.localData.MovieDetailsEntity
import com.example.streammoviesapplication.data.repository.IMoviesDetailsRepository
import com.example.streammoviesapplication.utils.resource.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val movieId: Int,
    val repository: IMoviesDetailsRepository
): ViewModel() {

    private val _movieDetails = MutableLiveData<Resource<List<MovieDetailsEntity>>>()
    val movieDetails: LiveData<Resource<List<MovieDetailsEntity>>> = _movieDetails

    init{
        fetchMovieDetails(movieId)
    }

    private fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch{
            _movieDetails.value = Resource.Loading()
                repository.fetchMovieDetails(movieId).collect(){result ->
                    when(result ){
                        is Resource.Success -> {
                            _movieDetails.value = Resource.Success(result.data)
                        }
                        is Resource.Error -> {
                            showError()
                        }
                        else -> {
                            Log.d("CHECK_LOADING", "NOT SET")
                        }
                    }

                }

            }


        }

    }

    private fun showError() {
        Log.d("CHECK_ERROR", "NOT SET")
    }

