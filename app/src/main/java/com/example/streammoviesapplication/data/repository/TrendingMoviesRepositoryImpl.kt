package com.example.streammoviesapplication.data.repository


import android.util.Log
import com.example.streammoviesapplication.data.db.TrendingMoviesDao
import com.example.streammoviesapplication.data.trendingMovies.localData.TrendingMoviesEntity
import com.example.streammoviesapplication.network.MovieService
import com.example.streammoviesapplication.utils.MovieMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrendingMoviesRepositoryImpl
@Inject constructor(
    private val api: MovieService,
    private val trendingMoviesDao: TrendingMoviesDao
): TrendingMoviesRepository {

    override suspend fun fetchTrendingMovies(): Flow<List<TrendingMoviesEntity>> {
        val response = api.getAllTrendingMovies()
        if(response.isSuccessful) {
            val movieList = response.body()?.results?: emptyList()
            val trendingMovieList = movieList.map { result ->
                MovieMapper.mapRemoteToEntity(result)
            }
            trendingMoviesDao.insertTrendingMovies(trendingMovieList)
        }else{
            Log.d("CHECK_DB", "Insertion for TrendingMovies failed -${response.message()}")
        }
        return trendingMoviesDao.getAllTrendingMovies()

    }

}