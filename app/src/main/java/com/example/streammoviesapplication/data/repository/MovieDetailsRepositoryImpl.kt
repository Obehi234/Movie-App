package com.example.streammoviesapplication.data.repository

import android.util.Log
import com.example.streammoviesapplication.data.db.MovieDetailsDao
import com.example.streammoviesapplication.data.model.localData.MovieDetailsEntity
import com.example.streammoviesapplication.data.model.localData.MovieResultEntity
import com.example.streammoviesapplication.network.MovieService
import com.example.streammoviesapplication.utils.resource.Resource
import com.example.streammoviesapplication.utils.resource.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val api: MovieService,
    private val movieDetailsDao: MovieDetailsDao

) : IMoviesDetailsRepository {
    override suspend fun fetchMovieDetails(id: Int): Flow<Resource<MovieDetailsEntity>>{
        return flow {
            when (val response = safeApiCall { api.getAllMovieDetailsById(id) }) {
                is Resource.Success -> {
                    val movieDetails = response.data
                    if (movieDetails != null) {
                        movieDetailsDao.insertMovieDetails(movieDetails)
                    }
                    emit(Resource.Success(movieDetailsDao.getMovieDetails(id)))
                    Log.d("CHECK_REPO", "${response.data}")
                }

                is Resource.Error -> {
                    emit(Resource.Error("${response.message}"))
                    Log.d("CHECK_REPO", "${response.message}")
                }

                is Resource.Loading -> {
                    emit(Resource.Loading())
                }
            }
        }
    }

}