package com.example.streammoviesapplication.data.repository

import android.util.Log
import com.example.streammoviesapplication.data.db.MovieListDao
import com.example.streammoviesapplication.data.model.localData.MovieResultEntity
import com.example.streammoviesapplication.data.model.mapper.MovieTabMapper
import com.example.streammoviesapplication.network.MovieService
import com.example.streammoviesapplication.utils.resource.Resource
import com.example.streammoviesapplication.utils.resource.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesTabRepositoryImpl
@Inject constructor(
    private val api: MovieService,
    private val movieTabDao: MovieListDao
): IMoviesTabRepository {
    override suspend fun fetchMoviesListTab(id: Int): Flow<Resource<List<MovieResultEntity>>> {
        return flow {
            when(val response = safeApiCall {  api.getMovieList(id)}) {
                is Resource.Success -> {
                    val movieTabResult = response.data?.results
                    val movieTabList = movieTabResult?.map { result ->
                        MovieTabMapper.mapRemoteTabToTabEntity(result)
                    }

                    if(movieTabList != null) {
                        movieTabDao.insertMovieList(movieTabList)
                    }
                    emit(Resource.Success(movieTabDao.getAllMovies()))
                    Log.d("CHECK_REPOSITORY", "$movieTabList")
                }
                is Resource.Error -> {
                    emit(Resource.Error("${response.message}"))
                    Log.d("CHECK_DB", "Database Insertion Failed - ${response.message}")
                }

                is Resource.Loading -> {
                    emit(Resource.Loading())
                    Log.d("CHECK_LOADING", "Data Loading, please wait...")
                }
            }
        }
    }
}