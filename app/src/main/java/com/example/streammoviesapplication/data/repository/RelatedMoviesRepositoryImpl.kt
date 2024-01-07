package com.example.streammoviesapplication.data.repository

import android.util.Log
import com.example.streammoviesapplication.data.db.RelatedMoviesDao
import com.example.streammoviesapplication.data.model.localData.RelatedMoviesEntity
import com.example.streammoviesapplication.data.model.mapper.RelatedMoviesMapper
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
    override suspend fun fetchRelatedMovies(id: Int) : Flow<Resource<List<RelatedMoviesEntity>>> {
        return flow {
            when(val response = safeApiCall { api.getRelatedMoviesById(id) }) {
                is Resource.Success -> {
                    val relatedMovies = response.data?.results
                    val relatedMovieList = relatedMovies?.map{result ->
                        RelatedMoviesMapper.mapRelatedMoviesRemoteToEntity(result)
                    }

                    if (relatedMovieList != null) {
                            relatedMoviesDao.insertRelatedMovies(relatedMovieList)
                    }

                    emit (Resource.Success(relatedMoviesDao.getRelatedMovies(id)))
                    Log.d("CHECK_RELATED MOVIES REPO", "SUCCESS - $relatedMovieList")
                }

                is Resource.Error -> {
                    emit(Resource.Error("${response.message}"))
                    Log.d("CHECK_RELATED MOVIES REPO", "ERROR")
                }

                is Resource.Loading -> {
                    emit(Resource.Loading())
                }
            }
        }
    }
}