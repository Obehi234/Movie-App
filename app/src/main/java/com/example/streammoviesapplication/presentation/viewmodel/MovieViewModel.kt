package com.example.streammoviesapplication.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streammoviesapplication.data.repository.TrendingMoviesRepository
import com.example.streammoviesapplication.data.trendingMovies.localData.TrendingMoviesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: TrendingMoviesRepository
): ViewModel() {
    private val _trendingMovieList =  MutableStateFlow<List<TrendingMoviesEntity>>(emptyList())
    val trendingMovieList = _trendingMovieList.asStateFlow()

init {
    viewModelScope.launch{
         try{ repository.fetchTrendingMovies().collect{trendingMovies ->
             _trendingMovieList.value = trendingMovies
             Log.d("CHECK FLOW", "Trending Movie List - $trendingMovies")
         }
    } catch (e: Exception) {
             Log.e("CHECK FLOW", "Error fetching trending movies: ${e.message}")

         }
    }
}

}