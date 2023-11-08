package com.example.streammoviesapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streammoviesapplication.data.model.localData.MovieResultEntity
import com.example.streammoviesapplication.utils.Constants

@Dao
interface MovieListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movies: List<MovieResultEntity>)

    @Query("SELECT * FROM ${Constants.MOVIE_LIST_TABLE}")
    suspend fun getAllMovies() : List<MovieResultEntity>
}