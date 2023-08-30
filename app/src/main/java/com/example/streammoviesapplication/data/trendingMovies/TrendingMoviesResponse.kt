package com.example.streammoviesapplication.data.trendingMovies

data class TrendingMoviesResponse(
    val page: Int,
    val results: List<TrendingMovies>,
    val total_pages: Int,
    val total_results: Int

)