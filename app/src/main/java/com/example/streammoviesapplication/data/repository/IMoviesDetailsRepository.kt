package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.model.localData.MovieDetailsEntity
import com.example.streammoviesapplication.utils.resource.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IMoviesDetailsRepository {
    suspend fun fetchMovieDetails(id: Int): Flow<Resource<MovieDetailsEntity>>
}