package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.model.localData.HorrorMoviesEntity
import com.example.streammoviesapplication.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

interface IHorrorMovieRepository {
    suspend fun fetchHorrorMovies() : Flow<Resource<List<HorrorMoviesEntity>>>
}