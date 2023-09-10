package com.example.streammoviesapplication.data.model.remoteData

data class TrendingMoviesResponse(
    val page: Int,
    val results: List<TrendingMovies>,
    val total_pages: Int,
    val total_results: Int

)