package com.example.streammoviesapplication.data.trendingMovies.localData

import androidx.room.TypeConverter

class ListTypeConverter {
    @TypeConverter
    fun convertListToString(list: List<Int>) : String {
        return list.joinToString ( separator = "," )
    }
}