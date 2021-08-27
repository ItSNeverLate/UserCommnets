package com.getyourguide.assignment.domain.useCase.review

import androidx.paging.PagingData
import com.getyourguide.assignment.domain.model.Review
import com.getyourguide.assignment.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetReviewsUseCaseImpl @Inject constructor(private val reviewRepository: ReviewRepository) :
    GetReviewsUseCase {
    override fun invoke(): Flow<PagingData<Review>> = reviewRepository.getReviews()
}