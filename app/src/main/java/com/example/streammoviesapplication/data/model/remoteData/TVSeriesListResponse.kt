package com.example.streammoviesapplication.data.model.remoteData

data class TVSeriesListResponse(
    val page: Int,
    val results: List<TabTVSeriesList>,
    val total_pages: Int,
    val total_results: Int
)