package com.example.streammoviesapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.streammoviesapplication.data.repository.TrendingMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val trendingMoviesRepository: TrendingMoviesRepository
): ViewModel() {





}