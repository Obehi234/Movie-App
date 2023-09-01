package com.example.streammoviesapplication.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListTypeConverter {
    @TypeConverter
    fun convertListToJsonString(list: List<Int>) : String = Gson().toJson(list)

    @TypeConverter
    fun convertJsonStringToList(data: String): List<Int> {
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(data, listType)
    }
}