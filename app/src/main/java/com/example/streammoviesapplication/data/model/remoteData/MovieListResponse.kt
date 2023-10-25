package com.example.streammoviesapplication.data.model.remoteData

data class MovieListResponse(
    val id: Int,
    val page: Int,
    val results: List<MovieList>,
    val total_pages: Int,
    val total_results: Int
)