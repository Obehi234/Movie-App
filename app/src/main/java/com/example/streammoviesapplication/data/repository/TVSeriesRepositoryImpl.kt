package com.example.streammoviesapplication.data.repository

import android.util.Log
import com.example.streammoviesapplication.data.db.TVSeriesDao
import com.example.streammoviesapplication.data.model.localData.TVSeriesEntity
import com.example.streammoviesapplication.data.model.mapper.TVSeriesMapper
import com.example.streammoviesapplication.network.MovieService
import com.example.streammoviesapplication.utils.resource.Resource
import com.example.streammoviesapplication.utils.resource.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TVSeriesRepositoryImpl
@Inject constructor(
    private val apiService: MovieService,
    private val tvSeriesDao: TVSeriesDao
): ITVSeriesRepository {
    override suspend fun fetchTVSeries() : Flow<Resource<List<TVSeriesEntity>>> {
        return flow {
            when(val response = safeApiCall { apiService.getTVSeries() }) {
                is Resource.Success -> {
                    val tvSeriesResult = response.data?.results
                    val tvSeriesList = tvSeriesResult?.map { result ->
                        TVSeriesMapper.mapRemoteTabToTVSeriesEntity(result)
                    }
                    if(tvSeriesList != null ){
                        tvSeriesDao.insertTVSeries(tvSeriesList)
                        Log.d("CHECK_TV_SERIES", "$tvSeriesList")
                        }
                    emit(Resource.Success(tvSeriesDao.getTVSeries()))

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