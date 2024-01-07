package com.example.streammoviesapplication.data.model.localData

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.streammoviesapplication.data.model.remoteData.movieDetails.Genre
import com.example.streammoviesapplication.utils.Constants


@Entity(tableName = Constants.RELATED_MOVIES_TABLE)
data class RelatedMoviesEntity (
    val adult: Boolean,
    val backdrop_path: String,
    @PrimaryKey
    val id: Int,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int

)