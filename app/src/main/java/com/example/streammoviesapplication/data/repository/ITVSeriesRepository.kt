package com.example.streammoviesapplication.data.repository

import com.example.streammoviesapplication.data.model.localData.TVSeriesEntity
import com.example.streammoviesapplication.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

interface ITVSeriesRepository{
    suspend fun fetchTVSeries() : Flow<Resource<List<TVSeriesEntity>>>
}