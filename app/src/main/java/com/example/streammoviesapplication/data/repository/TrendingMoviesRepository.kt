package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.trendingMovies.localData.TrendingMoviesEntity
import com.example.streammoviesapplication.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

interface TrendingMoviesRepository{
    suspend fun fetchTrendingMovies(): Flow<Resource<List<TrendingMoviesEntity>>>

}