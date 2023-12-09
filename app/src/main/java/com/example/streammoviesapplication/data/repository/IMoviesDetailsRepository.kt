package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.model.localData.MovieDetailsEntity
import com.example.streammoviesapplication.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

interface IMoviesDetailsRepository {
    suspend fun fetchMovieDetails(id: Int): Flow<Resource<List<MovieDetailsEntity>>>
}