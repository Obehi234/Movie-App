package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.db.HorrorMovieDao
import com.example.streammoviesapplication.data.model.localData.HorrorMoviesEntity
import com.example.streammoviesapplication.network.MovieService
import com.example.streammoviesapplication.utils.resource.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HorrorMovieRepositoryImpl
@Inject constructor(
    private val api: MovieService,
    private val horrorMovieDao: HorrorMovieDao

    ): IHorrorMovieRepository{
    override suspend fun fetchHorrorMovies(): Flow<Resource<List<HorrorMoviesEntity>>> {
        TODO("Not yet implemented")
    }
}