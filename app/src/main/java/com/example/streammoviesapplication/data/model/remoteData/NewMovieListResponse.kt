package com.example.streammoviesapplication.data.model.remoteData

data class NewMovieListResponse(
    val dates: Dates,
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)