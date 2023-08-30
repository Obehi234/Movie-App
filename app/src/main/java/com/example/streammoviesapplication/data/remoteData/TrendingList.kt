package com.example.streammoviesapplication.data.remoteData

data class TrendingList(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)