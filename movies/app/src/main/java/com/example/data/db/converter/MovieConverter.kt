package com.example.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

class MovieConverter {

    @TypeConverter
    fun toJson(movie: List<String>?) = Gson().toJson(movie)

    @TypeConverter
    fun fromJson(movie: String?) = Gson().fromJson(movie, Array<String>::class.java).toList()
}