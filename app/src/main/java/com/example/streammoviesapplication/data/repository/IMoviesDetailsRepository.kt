package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.model.localData.MovieDetailsEntity
import retrofit2.Response

interface IMoviesDetailsRepository {
    suspend fun fetchMovieDetails(id: Int): Response<MovieDetailsEntity>
}