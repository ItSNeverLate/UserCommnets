package com.getyourguide.assignment.presentation.util

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    fun getFormattedDateTime(
        dateTimeStr: String,
        dateTimePattern: String? = "MMMM d, yyyy",
    ): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault())
        val date = dateFormat.parse(dateTimeStr)
        return date?.let {
            SimpleDateFormat(dateTimePattern, Locale.getDefault()).format(it) ?: ""
        } ?: ""
    }
}
