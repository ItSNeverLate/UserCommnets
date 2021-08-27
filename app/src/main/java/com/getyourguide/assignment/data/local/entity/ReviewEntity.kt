package com.getyourguide.assignment.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey
    val id: Int,
    val activityId: Int,
    val author: AuthorEntity,
    val created: String,
    val enjoyment: String,
    val isAnonymous: Boolean,
    val language: String?,
    val message: String,
    val optionId: Int,
    val rating: Float,
    val title: String,
    val travelerType: String?,
)