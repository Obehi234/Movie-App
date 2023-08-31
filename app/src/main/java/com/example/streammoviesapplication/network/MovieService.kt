package com.example.streammoviesapplication.network

import com.example.streammoviesapplication.data.trendingMovies.TrendingMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface MovieService {

    @GET("/3/trending/movie/day")
    suspend fun getAllTrendingMovies(
        @Header("apiKey") apiKey: String,
        @Query("language")  language: String
    ) : Response<TrendingMoviesResponse>
}