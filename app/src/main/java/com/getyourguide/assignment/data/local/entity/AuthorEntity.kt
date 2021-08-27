package com.getyourguide.assignment.data.local.entity

/** We don't persist it directly into DB
 * We use Converter to save it as a Json String
  */
data class AuthorEntity(
    val fullName: String,
    val country: String?,
    val photo: String?
)