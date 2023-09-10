package com.example.streammoviesapplication.data.model.localData

data class MovieViewState(
    val isLoading: Boolean = false,
    val trendingMovies: List<TrendingMoviesEntity>? = emptyList(),
    val errorMessage: String? = null
)
