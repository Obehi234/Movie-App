package com.example.streammoviesapplication.data.model.remoteData.relatedMovies

data class RelatedMoviesResponse(
    val page: Int,
    val results: List<RelatedMovieList>,
    val total_pages: Int,
    val total_results: Int
)