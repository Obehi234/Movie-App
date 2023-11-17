package com.example.streammoviesapplication.data.model.localData

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.streammoviesapplication.utils.Constants

@Entity(tableName = Constants.MOVIE_LIST_TABLE)
data class MovieResultEntity(
    @PrimaryKey
    val id: Int,
    val adult: Boolean? = false,
    val backdrop_path: String? = "",
    val genre_ids: List<Int>? = emptyList(),
    val original_language: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val poster_path: String? = "",
    val release_date: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0
)

val result = listOf(
    MovieResultEntity(
        title = "Aladinn",
        id = 0
    ),
    MovieResultEntity(
        title = "Sleeping Beauty",
        id = 1
    ),
    MovieResultEntity(
        title = "Queen Bee",
        id = 2
    )
)