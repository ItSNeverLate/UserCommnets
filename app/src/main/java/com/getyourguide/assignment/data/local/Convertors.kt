package com.getyourguide.assignment.data.local

import androidx.room.TypeConverter
import com.getyourguide.assignment.data.local.entity.AuthorEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Convertors {

    private val gson = Gson()

    @TypeConverter
    fun toAuthorEntity(data: String?): AuthorEntity? =
        data?.let {
            val listType = object : TypeToken<AuthorEntity>() {}.type
            return gson.fromJson(it, listType)
        }

    @TypeConverter
    fun fromAuthorEntity(author: AuthorEntity): String = gson.toJson(author)
}