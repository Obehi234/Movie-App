package com.example.streammoviesapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streammoviesapplication.data.model.localData.DocumentaryEntity
import com.example.streammoviesapplication.utils.Constants

@Dao
interface DocumentaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocumentaryList(documentaries: List<DocumentaryEntity>)

    @Query("SELECT * FROM ${Constants.DOCUMENTARY_TABLE}")
    suspend fun getDocumentaries() : List<DocumentaryEntity>
}