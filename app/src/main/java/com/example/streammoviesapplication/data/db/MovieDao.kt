package com.example.streammoviesapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streammoviesapplication.data.trendingMovies.localData.TrendingMoviesEntity
import com.example.streammoviesapplication.utils.Constants.MOVIE_DATABASE

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingMoviesToDatabase(movies: List<TrendingMoviesEntity>)

    @Query("SELECT * FROM $MOVIE_DATABASE")
    suspend fun getAllTrendingMovies() : List<TrendingMoviesEntity>


}