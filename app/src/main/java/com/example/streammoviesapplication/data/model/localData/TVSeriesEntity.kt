package com.example.streammoviesapplication.data.model.localData

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.streammoviesapplication.utils.Constants

@Entity(tableName = Constants.TV_SERIES_TABLE)
data class TVSeriesEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean,
    val backdropPath: String? = "",
    val firstAirDate: String,
    val name: String,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
)
