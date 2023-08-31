package com.example.streammoviesapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streammoviesapplication.data.repository.TrendingMoviesRepository
import com.example.streammoviesapplication.data.trendingMovies.localData.TrendingMoviesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: TrendingMoviesRepository
): ViewModel() {

init {
    viewModelScope.launch{
        val trendingMovieList = repository.fetchTrendingMovies()
    }
}





}