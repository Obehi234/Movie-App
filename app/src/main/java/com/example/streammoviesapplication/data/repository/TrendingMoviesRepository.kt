package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.db.TrendingMoviesDao
import com.example.streammoviesapplication.network.MovieService
import javax.inject.Inject

class TrendingMoviesRepository @Inject constructor(private val api: MovieService,
private val trendingMoviesDao: TrendingMoviesDao) {


}