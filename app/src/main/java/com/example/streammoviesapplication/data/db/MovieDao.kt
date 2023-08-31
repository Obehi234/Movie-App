package com.example.streammoviesapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.streammoviesapplication.data.trendingMovies.localData.TrendingMoviesEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingMoviesToDatabase(movies: List<TrendingMoviesEntity>)


}