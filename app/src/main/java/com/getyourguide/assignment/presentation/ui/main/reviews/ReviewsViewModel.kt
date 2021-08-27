package com.getyourguide.assignment.presentation.ui.main.reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.getyourguide.assignment.domain.useCase.review.GetReviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(
    getReviewsUseCase: GetReviewsUseCase,
) : ViewModel() {

    val reviews = getReviewsUseCase.invoke().cachedIn(viewModelScope)
}