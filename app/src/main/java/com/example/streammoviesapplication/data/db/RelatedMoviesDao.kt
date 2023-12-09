package com.example.streammoviesapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streammoviesapplication.data.model.localData.RelatedMoviesEntity
import com.example.streammoviesapplication.utils.Constants

@Dao
interface RelatedMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRelatedMovies(relatedMovies: List<RelatedMoviesEntity>)

    @Query("SELECT * FROM ${Constants.RELATED_MOVIES_TABLE} WHERE id = :id")
    suspend fun getRelatedMovies(id: Int) : RelatedMoviesEntity

}