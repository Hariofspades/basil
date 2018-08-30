package com.hariofspades.data.mapper

import com.hariofspades.data.model.RecipesEntity
import com.hariofspades.domain.model.Recipes

class RecipesEntityMapper : EntityMapper<RecipesEntity, Recipes> {

    override fun mapFromEntity(entity: RecipesEntity): Recipes {
        return Recipes(
                entity.id,
                entity.publisher,
                entity.siteUrl,
                entity.title,
                entity.sourceUrl,
                entity.imageUrl,
                entity.rating,
                entity.publisherUrl,
                entity.isBookmarked,
                entity.category
        )
    }

    override fun mapToEntity(domain: Recipes): RecipesEntity {
        return RecipesEntity(
                domain.id,
                domain.publisher,
                domain.siteUrl,
                domain.title,
                domain.sourceUrl,
                domain.imageUrl,
                domain.rating,
                domain.publisherUrl,
                domain.isBookmarked,
                domain.category
        )
    }
}