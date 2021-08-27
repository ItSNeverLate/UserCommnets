package com.getyourguide.assignment.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Author(
    val fullName: String,
    val country: String?,
    val photo: String?,
) : Parcelable