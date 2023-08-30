package com.example.streammoviesapplication.data.trendingData

data class TrendingMoviesResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)