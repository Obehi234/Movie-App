package com.example.streammoviesapplication.network

import com.example.streammoviesapplication.data.model.localData.MovieDetailsEntity
import com.example.streammoviesapplication.data.model.localData.RelatedMoviesEntity
import com.example.streammoviesapplication.data.model.remoteData.NewMovieListResponse
import com.example.streammoviesapplication.data.model.remoteData.TrendingMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface MovieService {
    @GET("/3/trending/movie/day")
    suspend fun getAllTrendingMovies(
    ) : Response<TrendingMoviesResponse>

    @GET("/3/discover/movie")
    suspend fun getMovieList() : Response<NewMovieListResponse>

    @GET("/3/movie/{movie_id}")
    suspend fun getAllMovieDetailsById(@Path("movie_id")id: Int): Response<MovieDetailsEntity>

    @GET("/3/movie/{movie_id}/similar")
    suspend fun getRelatedMoviesById(@Path("movie_id") id: Int): Response<RelatedMoviesEntity>
}

