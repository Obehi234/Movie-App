package com.example.streammoviesapplication.data.db

import androidx.room.TypeConverter
import com.example.streammoviesapplication.data.model.remoteData.movieDetails.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreTypeConverter {
    @TypeConverter
    fun convertGenreListToString(genreList: List<Genre>?): String? = Gson().toJson(genreList)

    @TypeConverter
    fun convertStringToGenreList(data: String?): List<Genre>? {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(data, listType)
    }
}
