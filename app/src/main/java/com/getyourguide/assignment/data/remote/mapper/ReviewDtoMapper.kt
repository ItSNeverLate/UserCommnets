package com.getyourguide.assignment.data.remote.mapper

import com.getyourguide.assignment.data.remote.dto.ReviewDto
import com.getyourguide.assignment.domain.model.Review
import com.getyourguide.assignment.domain.util.Mapper

/**
 * If you want to use remote data as the data source
 */
@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "unused")
object ReviewDtoMapper : Mapper<ReviewDto, Review> {

    override fun toModel(dto: ReviewDto) = Review(
        id = dto.id,
        activityId = dto.activityId,
        author = AuthorDtoMapper.toModel(dto.author),
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

    override fun fromModel(model: Review) =
        ReviewDto(
            id = model.id,
            activityId = model.activityId,
            author = AuthorDtoMapper.fromModel(model.author),
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

    override fun fromModelList(list: List<Review>): List<ReviewDto> =
        list.map {
            fromModel(it)
        }

    override fun toModelList(list: List<ReviewDto>): List<Review> = list.map {
        toModel(it)
    }
}