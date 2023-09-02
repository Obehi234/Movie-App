package com.example.streammoviesapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streammoviesapplication.data.trendingMovies.localData.TrendingMoviesEntity
import com.example.streammoviesapplication.utils.Constants
import com.example.streammoviesapplication.utils.Constants.MOVIE_DATABASE
import com.example.streammoviesapplication.utils.Constants.TRENDING_MOVIES_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingMovies(movies: List<TrendingMoviesEntity>)

    @Query("SELECT * FROM $TRENDING_MOVIES_TABLE")
   suspend fun getAllTrendingMovies() : List<TrendingMoviesEntity>


}