package com.example.streammoviesapplication.data.db

import androidx.room.TypeConverter
import com.example.streammoviesapplication.data.model.remoteData.movieDetails.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreTypeConverter {
    @TypeConverter
    fun convertGenreToString(genre: Genre?): String? = Gson().toJson(genre)

    @TypeConverter
    fun convertStringToGenre(data: String?): Genre? {
        val type = object : TypeToken<Genre>() {}.type
        return Gson().fromJson(data, type)
    }
}
