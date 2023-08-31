package com.example.streammoviesapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streammoviesapplication.data.trendingMovies.localData.TrendingMoviesEntity
import com.example.streammoviesapplication.utils.Constants.MOVIE_DATABASE
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingMovies(movies: List<TrendingMoviesEntity>)

    @Query("SELECT * FROM $MOVIE_DATABASE")
    fun getAllTrendingMovies() : Flow<List<TrendingMoviesEntity>>


}