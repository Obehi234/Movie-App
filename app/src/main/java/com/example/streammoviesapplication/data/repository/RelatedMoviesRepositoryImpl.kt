package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.db.RelatedMoviesDao
import com.example.streammoviesapplication.data.model.localData.RelatedMoviesEntity
import com.example.streammoviesapplication.network.MovieService
import com.example.streammoviesapplication.utils.resource.Resource
import com.example.streammoviesapplication.utils.resource.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RelatedMoviesRepositoryImpl
@Inject constructor(
    private val api: MovieService,
    val relatedMoviesDao: RelatedMoviesDao
): IRelatedMoviesRepository {
    override suspend fun fetchRelatedMovies(id: Int) : Flow<Resource<RelatedMoviesEntity>> {
        return flow {
            when(val response = safeApiCall { api.getRelatedMoviesById(id) }) {
                is Resource.Success -> {
                    val relatedMovies = response.data
                    if(relatedMovies != null) {
                        relatedMoviesDao.insertRelatedMovies(listOf(relatedMovies))
                    }
                    emit (Resource.Success(relatedMoviesDao.getRelatedMovies(id)))
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