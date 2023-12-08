package com.example.streammoviesapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.streammoviesapplication.data.model.localData.MovieDetailsEntity
import com.example.streammoviesapplication.data.model.localData.MovieResultEntity
import com.example.streammoviesapplication.data.model.localData.RelatedMoviesEntity
import com.example.streammoviesapplication.data.model.localData.TrendingMoviesEntity

@Database(
    entities = [
        TrendingMoviesEntity::class,
        MovieResultEntity::class,
        MovieDetailsEntity::class,
        RelatedMoviesEntity::class
    ], version = 1, exportSchema = false
)
@TypeConverters(ListTypeConverter::class, GenreTypeConverter::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun trendingMoviesDao(): TrendingMoviesDao
    abstract fun movieListDao(): MovieListDao
    abstract fun movieDetailsDao() : MovieDetailsDao

    abstract fun relatedMoviesDao() : RelatedMoviesDao
}