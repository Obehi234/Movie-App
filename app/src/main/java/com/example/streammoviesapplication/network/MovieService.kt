package com.example.streammoviesapplication.network

import com.example.streammoviesapplication.data.model.localData.MovieDetailsEntity
import com.example.streammoviesapplication.data.model.localData.RelatedMoviesEntity
import com.example.streammoviesapplication.data.model.remoteData.DocumentaryResponse
import com.example.streammoviesapplication.data.model.remoteData.NewMovieListResponse
import com.example.streammoviesapplication.data.model.remoteData.TVSeriesListResponse
import com.example.streammoviesapplication.data.model.remoteData.TrendingMoviesResponse
import com.example.streammoviesapplication.data.model.remoteData.relatedMovies.RelatedMoviesResponse
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
    suspend fun getRelatedMoviesById(@Path("movie_id") id: Int): Response<RelatedMoviesResponse>

    @GET("/3/tv/airing_today")
    suspend fun getTVSeries() : Response<TVSeriesListResponse>

    @GET("")
    suspend fun getDocumentary() : Response<DocumentaryResponse>
}

