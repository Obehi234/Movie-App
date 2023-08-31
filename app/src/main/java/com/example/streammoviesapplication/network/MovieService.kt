package com.example.streammoviesapplication.network

import com.example.streammoviesapplication.data.trendingMovies.remoteData.TrendingMoviesResponse
import retrofit2.Response
import retrofit2.http.GET


interface MovieService {

    @GET("/3/trending/movie/day")
    suspend fun getAllTrendingMovies(
    ) : Response<TrendingMoviesResponse>
}