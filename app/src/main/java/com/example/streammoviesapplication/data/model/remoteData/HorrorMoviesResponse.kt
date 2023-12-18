package com.example.streammoviesapplication.data.model.remoteData

data class HorrorMoviesResponse(
    val page: Int,
    val results: List<HorrorMovieList>,
    val total_pages: Int,
    val total_results: Int
)