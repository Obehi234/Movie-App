package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.db.HorrorMovieDao
import com.example.streammoviesapplication.data.model.localData.HorrorMoviesEntity
import com.example.streammoviesapplication.network.MovieService
import com.example.streammoviesapplication.utils.resource.Resource
import com.example.streammoviesapplication.utils.resource.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HorrorMovieRepositoryImpl
@Inject constructor(
    private val api: MovieService,
    private val horrorMovieDao: HorrorMovieDao

    ): IHorrorMovieRepository{
    override suspend fun fetchHorrorMovies(): Flow<Resource<List<HorrorMoviesEntity>>> {
        return flow {
            when(val response = safeApiCall { api.getHorrorMovies(27) }) {
                is Resource.Success -> {

                }
            }
        }

    }
}