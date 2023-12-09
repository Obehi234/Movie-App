package com.example.streammoviesapplication.data.repository

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
    override suspend fun fetchMovieDetails(id: Int): Flow<Resource<List<MovieDetailsEntity>>>{
        return flow {
            when (val response = safeApiCall { api.getAllMovieDetailsById(id) }) {
                is Resource.Success -> {
                    val movieDetails = response.data
                    if (movieDetails != null) {
                        movieDetailsDao.insertMovieDetails(listOf(movieDetails))
                    }
                    emit(Resource.Success(movieDetailsDao.getMovieDetails(id)))
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