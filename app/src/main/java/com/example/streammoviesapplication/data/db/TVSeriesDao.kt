package com.example.streammoviesapplication.data.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streammoviesapplication.data.model.localData.TVSeriesEntity
import com.example.streammoviesapplication.utils.Constants

interface TVSeriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVSeries(tvSeries: List<TVSeriesEntity>)

    @Query("SELECT * FROM ${Constants.TV_SERIES_TABLE}")
    suspend fun getTVSeries() : List<TVSeriesEntity>
}