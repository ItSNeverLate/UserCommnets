package com.getyourguide.assignment.data.remote.response

import com.getyourguide.assignment.data.remote.dto.ReviewDto

data class ReviewResponse(
    val reviews: List<ReviewDto>,
    val totalCount: Long,
    val averageRating: Double,
    val pagination: Pagination,
)