package com.getyourguide.assignment.domain.useCase.review

import androidx.paging.PagingData
import com.getyourguide.assignment.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface GetReviewsUseCase {
    operator fun invoke(): Flow<PagingData<Review>>
}