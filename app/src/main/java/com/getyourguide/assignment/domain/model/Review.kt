package com.getyourguide.assignment.domain.model

import android.os.Parcelable
import com.getyourguide.assignment.presentation.util.DateTimeUtils
import kotlinx.android.parcel.Parcelize

/**
 * "Parcelize" for passing data through navigation safeargs
 */
@Parcelize
data class Review(
    val id: Int,
    val activityId: Int,
    val author: Author,
    val created: String,
    val enjoyment: String,
    val isAnonymous: Boolean,
    val language: String,
    val message: String,
    val optionId: Int,
    val rating: Float,
    val title: String,
    val travelerType: String?,
) : Parcelable {
    val createdDate: String
        get() = DateTimeUtils.getFormattedDateTime(created)
}