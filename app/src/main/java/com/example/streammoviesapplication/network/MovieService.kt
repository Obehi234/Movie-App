package com.example.streammoviesapplication.network

import com.example.streammoviesapplication.data.model.remoteData.NewMovieListResponse
import com.example.streammoviesapplication.data.model.remoteData.TrendingMoviesResponse
import retrofit2.Response
import retrofit2.http.GET


interface MovieService {
    @GET("/3/trending/movie/day")
    suspend fun getAllTrendingMovies(
    ) : Response<TrendingMoviesResponse>

    @GET("/3/discover/movie")
    suspend fun getMovieList() : Response<NewMovieListResponse>
}

