package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.model.localData.RelatedMoviesEntity
import com.example.streammoviesapplication.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

interface IRelatedMoviesRepository {
    suspend fun fetchRelatedMovies(id: Int) : Flow<Resource<List<RelatedMoviesEntity>>>
}