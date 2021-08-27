package com.getyourguide.assignment.data.local.mapper

import com.getyourguide.assignment.data.local.entity.ReviewEntity
import com.getyourguide.assignment.data.remote.dto.ReviewDto
import com.getyourguide.assignment.domain.model.Review
import com.getyourguide.assignment.domain.util.Mapper

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
object ReviewEntityMapper : Mapper<ReviewEntity, Review> {

    override fun toModel(entity: ReviewEntity) = Review(
        id = entity.id,
        activityId = entity.activityId,
        author = AuthorEntityMapper.toModel(entity.author),
        created = entity.created,
        enjoyment = entity.enjoyment,
        isAnonymous = entity.isAnonymous,
        language = entity.language ?: "",
        message = entity.message,
        optionId = entity.optionId,
        rating = entity.rating,
        title = entity.title,
        travelerType = entity.travelerType,
    )

    override fun fromModel(model: Review) =
        ReviewEntity(
            id = model.id,
            activityId = model.activityId,
            author = AuthorEntityMapper.fromModel(model.author),
            created = model.created,
            enjoyment = model.enjoyment,
            isAnonymous = model.isAnonymous,
            language = model.language,
            message = model.message,
            optionId = model.optionId,
            rating = model.rating,
            title = model.title,
            travelerType = model.travelerType,
        )

    override fun fromModelList(list: List<Review>): List<ReviewEntity> =
        list.map {
            fromModel(it)
        }

    override fun toModelList(list: List<ReviewEntity>): List<Review> = list.map {
        toModel(it)
    }

    private fun fromDto(dto: ReviewDto) =
        ReviewEntity(
            id = dto.id,
            activityId = dto.activityId,
            author = AuthorEntityMapper.fromDto(dto.author),
            created = dto.created,
            enjoyment = dto.enjoyment,
            isAnonymous = dto.isAnonymous,
            language = dto.language,
            message = dto.message,
            optionId = dto.optionId,
            rating = dto.rating,
            title = dto.title,
            travelerType = dto.travelerType,
        )

    fun fromDtoList(list: List<ReviewDto>): List<ReviewEntity> =
        list.map {
            fromDto(it)
        }
}