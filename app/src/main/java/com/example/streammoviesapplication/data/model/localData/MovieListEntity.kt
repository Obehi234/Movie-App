package com.example.streammoviesapplication.data.model.localData

import androidx.room.PrimaryKey

data class MovieListEntity(
    @PrimaryKey
    val id: Int,
    val page: Int,
    val description: String,
    val favorite_count: Int,
    val iso_639_1: String,
    val item_count: Int,
    val list_type: String,
    val name: String,
    val poster_path: Any
)
