package com.example.streammoviesapplication.data.repository


import android.util.Log
import com.example.streammoviesapplication.data.db.TrendingMoviesDao
import com.example.streammoviesapplication.data.model.localData.TrendingMoviesEntity
import com.example.streammoviesapplication.network.MovieService
import com.example.streammoviesapplication.data.model.mapper.MovieMapper
import com.example.streammoviesapplication.utils.resource.Resource
import com.example.streammoviesapplication.utils.resource.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TrendingMoviesRepositoryImpl
@Inject constructor(
    private val api: MovieService,
    private val trendingMoviesDao: TrendingMoviesDao
): ITrendingMoviesRepository {

    override suspend fun fetchTrendingMovies(): Flow<Resource<List<TrendingMoviesEntity>>>{

        return flow {
            when (val response = safeApiCall { api.getAllTrendingMovies() }) {
                is Resource.Success -> {
                    val movieList = response.data?.results
                    val trendingMovieList = movieList?.map { result ->
                        MovieMapper.mapRemoteToEntity(result)
                    }
                    if (trendingMovieList != null) {
                        trendingMoviesDao.insertTrendingMovies(trendingMovieList)
                    }
                    emit(Resource.Success(trendingMoviesDao.getAllTrendingMovies()))
                }

                is Resource.Error -> {
                    emit(Resource.Error("${response.message}"))
                }

                is Resource.Loading -> {
                    emit(Resource.Loading())
                }
            }
        }

    }

}