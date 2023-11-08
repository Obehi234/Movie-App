package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.model.localData.TrendingMoviesEntity
import com.example.streammoviesapplication.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

interface ITrendingMoviesRepository{
    suspend fun fetchTrendingMovies(): Flow<Resource<List<TrendingMoviesEntity>>>

}