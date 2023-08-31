package com.example.streammoviesapplication.data.repository


import android.util.Log
import com.example.streammoviesapplication.data.db.TrendingMoviesDao
import com.example.streammoviesapplication.data.trendingMovies.localData.TrendingMoviesEntity
import com.example.streammoviesapplication.network.MovieService
import com.example.streammoviesapplication.utils.MovieMapper
import javax.inject.Inject

class TrendingMoviesRepository
@Inject constructor(
    private val api: MovieService,
    private val trendingMoviesDao: TrendingMoviesDao
) {

    suspend fun fetchTrendingMovies(): List<TrendingMoviesEntity> {
        return try {
            val response = api.getAllTrendingMovies()
            if (response.isSuccessful) {
                val trendingMovieList = response.body()?.results ?: emptyList()
                val movieEntities = trendingMovieList.map { result ->
                    MovieMapper.mapRemoteToEntity(result)
                }
                trendingMoviesDao.insertTrendingMovies(movieEntities)
                trendingMoviesDao.getAllTrendingMovies()
            } else {
                Log.d("CHECK_DB", "Insert Trending Movies Failed! ${response.message()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("CHECK_DB", "Error fetching Trending movies ${e.message}")
            emptyList()
        }
    }


}