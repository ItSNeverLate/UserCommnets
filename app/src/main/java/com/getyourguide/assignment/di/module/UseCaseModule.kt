package com.getyourguide.assignment.di.module

import com.getyourguide.assignment.domain.repository.ReviewRepository
import com.getyourguide.assignment.domain.useCase.review.GetReviewsUseCase
import com.getyourguide.assignment.domain.useCase.review.GetReviewsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetReviewsUseCase(reviewRepository: ReviewRepository): GetReviewsUseCase =
        GetReviewsUseCaseImpl(reviewRepository)
}