package com.example.streammoviesapplication.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ObjectTypeConverter {
    @TypeConverter
    fun convertObjectToString(obj: Any?): String? = Gson().toJson(obj)

    @TypeConverter
    fun convertStringToObject(data: String?): Any? {
        val type = object : TypeToken<Any>() {}.type
        return Gson().fromJson(data, type)
    }
}
