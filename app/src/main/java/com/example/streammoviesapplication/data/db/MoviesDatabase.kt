package com.example.streammoviesapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.streammoviesapplication.data.trendingMovies.localData.TrendingMoviesEntity

@Database(entities = [TrendingMoviesEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun trendingMoviesDao() : TrendingMoviesDao
}