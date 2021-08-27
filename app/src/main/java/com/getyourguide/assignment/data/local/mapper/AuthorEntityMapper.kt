package com.getyourguide.assignment.data.local.mapper

import com.getyourguide.assignment.data.local.entity.AuthorEntity
import com.getyourguide.assignment.data.remote.dto.AuthorDto
import com.getyourguide.assignment.domain.model.Author
import com.getyourguide.assignment.domain.util.Mapper

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
object AuthorEntityMapper : Mapper<AuthorEntity, Author> {

    override fun toModel(entity: AuthorEntity) = Author(
        fullName = entity.fullName,
        country = entity.country,
        photo = entity.photo,
    )

    override fun fromModel(model: Author) =
        AuthorEntity(
            fullName = model.fullName,
            country = model.country,
            photo = model.photo,
        )

    override fun fromModelList(list: List<Author>): List<AuthorEntity> =
        list.map {
            fromModel(it)
        }

    override fun toModelList(list: List<AuthorEntity>): List<Author> = list.map {
        toModel(it)
    }

    fun fromDto(dto: AuthorDto) =
        AuthorEntity(
            fullName = dto.fullName,
            country = dto.country,
            photo = dto.photo,
        )
}