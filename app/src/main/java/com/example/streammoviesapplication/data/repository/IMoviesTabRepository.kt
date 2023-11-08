package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.model.localData.MovieResultEntity
import com.example.streammoviesapplication.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

interface IMoviesTabRepository {
    suspend fun fetchMoviesListTab(id: Int) : Flow<Resource<List<MovieResultEntity>>>
}