package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.trendingMovies.localData.TrendingMoviesEntity
import kotlinx.coroutines.flow.Flow

interface TrendingMoviesRepository{
    suspend fun fetchTrendingMovies(): Flow<List<TrendingMoviesEntity>>

}