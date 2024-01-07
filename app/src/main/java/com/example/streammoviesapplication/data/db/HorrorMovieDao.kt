package com.example.streammoviesapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streammoviesapplication.data.model.localData.HorrorMoviesEntity
import com.example.streammoviesapplication.utils.Constants

@Dao
interface HorrorMovieDao {
       @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertHorrorMovieList(documentaries: List<HorrorMoviesEntity>)

       @Query("SELECT * FROM ${Constants.HORROR_MOVIES_TABLE}")
        suspend fun getHorrorMovies() : List<HorrorMoviesEntity>
}