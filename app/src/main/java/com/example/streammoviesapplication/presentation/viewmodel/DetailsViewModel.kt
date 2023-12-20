package com.example.streammoviesapplication.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streammoviesapplication.data.model.localData.MovieDetailsEntity
import com.example.streammoviesapplication.data.model.localData.RelatedMoviesEntity
import com.example.streammoviesapplication.data.repository.IMoviesDetailsRepository
import com.example.streammoviesapplication.data.repository.IRelatedMoviesRepository
import com.example.streammoviesapplication.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: IMoviesDetailsRepository,
    private val relatedMovieRepository: IRelatedMoviesRepository
) : ViewModel(){
    private val _movieDetails = MutableLiveData<Resource<MovieDetailsEntity>>()
    val movieDetails: LiveData<Resource<MovieDetailsEntity>> = _movieDetails

    private val _relatedMovieDetails = MutableLiveData<Resource<List<RelatedMoviesEntity>>>()
    val relatedMovieDetails : LiveData<Resource<List<RelatedMoviesEntity>>> = _relatedMovieDetails
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

    fun fetchRelatedMovies(movieId: Int) {
        viewModelScope.launch {
            try {
                _relatedMovieDetails.value = Resource.Loading()

                relatedMovieRepository.fetchRelatedMovies(movieId).collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _relatedMovieDetails.value = result
                            Log.d("CHECK_VM_RELATED", "Related Movies Result - ${result.data}")
                        }

                        is Resource.Error -> {
                            val errorMessage = result.message ?: "Unknown error"
                            Log.e("CHECK_VM_REL", "Related Movies Error - $errorMessage")
                            _relatedMovieDetails.value = Resource.Error(errorMessage, null)
                        }

                        is Resource.Loading -> {
                            // You can handle loading states if needed
                            Log.d("CHECK_VM", "Related Movies Loading")
                        }
                    }
                }
            } catch (e: Exception) {
                _relatedMovieDetails.value = Resource.Error(e.message ?: "An error occurred", null)
            }
        }
    }

}