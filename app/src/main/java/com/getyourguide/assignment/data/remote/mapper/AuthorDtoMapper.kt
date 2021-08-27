package com.getyourguide.assignment.data.remote.mapper

import com.getyourguide.assignment.data.remote.dto.AuthorDto
import com.getyourguide.assignment.domain.model.Author
import com.getyourguide.assignment.domain.util.Mapper

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
object AuthorDtoMapper : Mapper<AuthorDto, Author> {

    override fun toModel(dto: AuthorDto) = Author(
        fullName = dto.fullName,
        country = dto.country,
        photo = dto.photo,
    )

    override fun fromModel(model: Author) =
        AuthorDto(
            fullName = model.fullName,
            country = model.country,
            photo = model.photo,
        )

    override fun fromModelList(list: List<Author>): List<AuthorDto> =
        list.map {
            fromModel(it)
        }

    override fun toModelList(list: List<AuthorDto>): List<Author> = list.map {
        toModel(it)
    }
}