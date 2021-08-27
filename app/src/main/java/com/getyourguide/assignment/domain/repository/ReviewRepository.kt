package com.getyourguide.assignment.domain.repository

import androidx.paging.PagingData
import com.getyourguide.assignment.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {

    fun getReviews(): Flow<PagingData<Review>>
}