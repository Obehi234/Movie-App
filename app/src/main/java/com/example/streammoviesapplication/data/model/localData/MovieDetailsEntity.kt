package com.example.streammoviesapplication.data.model.localData

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.streammoviesapplication.data.model.remoteData.movieDetails.Genre
import com.example.streammoviesapplication.data.model.remoteData.movieDetails.ProductionCompany
import com.example.streammoviesapplication.data.model.remoteData.movieDetails.ProductionCountry
import com.example.streammoviesapplication.data.model.remoteData.movieDetails.SpokenLanguage
import com.example.streammoviesapplication.utils.Constants


@Entity(tableName = Constants.DETAILS_TABLE)
data class MovieDetailsEntity(
    val adult: Boolean,
    val backdrop_path: String,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    @PrimaryKey
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,

)