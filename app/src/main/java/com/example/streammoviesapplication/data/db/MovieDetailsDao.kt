package com.example.streammoviesapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streammoviesapplication.data.model.localData.MovieDetailsEntity
import com.example.streammoviesapplication.utils.Constants

@Dao
interface MovieDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(movieDetails: List<MovieDetailsEntity>)

    @Query("SELECT * FROM ${Constants.DETAILS_TABLE} WHERE id = :id")
    suspend fun getMovieDetails(id: Int) :List< MovieDetailsEntity>
}