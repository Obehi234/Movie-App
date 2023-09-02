package com.example.streammoviesapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streammoviesapplication.data.repository.TrendingMoviesRepository
import com.example.streammoviesapplication.data.trendingMovies.localData.TrendingMoviesEntity
import com.example.streammoviesapplication.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: TrendingMoviesRepository
): ViewModel() {

    private val _trendingMovieList = MutableStateFlow<Resource<List<TrendingMoviesEntity>>>(Resource.Loading())
    val trendingMovieList: StateFlow<Resource<List<TrendingMoviesEntity>>> = _trendingMovieList.asStateFlow()

    init {
        fetchTrendingMovies()
    }
    private fun fetchTrendingMovies() {
        viewModelScope.launch {
            try {
                val response = repository.fetchTrendingMovies()
                response.collect{result ->
                    _trendingMovieList.value = result
                }
            } catch (e: Exception) {
                _trendingMovieList.value = Resource.Error("Something went wrong")
            }

        }
    }
}