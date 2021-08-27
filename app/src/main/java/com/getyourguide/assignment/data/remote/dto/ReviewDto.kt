package com.getyourguide.assignment.data.remote.dto

data class ReviewDto(
    val id: Int,
    val activityId: Int,
    val author: AuthorDto,
    val created: String,
    val enjoyment: String,
    val isAnonymous: Boolean,
    val language: String,
    val message: String,
    val optionId: Int,
    val rating: Float,
    val title: String,
    val travelerType: String?,
)