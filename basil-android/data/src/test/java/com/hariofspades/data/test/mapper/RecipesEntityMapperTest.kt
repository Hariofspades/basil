package com.hariofspades.data.test.mapper

import com.hariofspades.data.mapper.RecipesEntityMapper
import com.hariofspades.data.model.RecipesEntity
import com.hariofspades.data.test.data.DataFactoryOutlet
import com.hariofspades.domain.model.Recipes
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RecipesEntityMapperTest {

    private val mapper = RecipesEntityMapper()

    @Test
    fun `recipes entity mapper maps data from entity to domain`() {
        val entity = DataFactoryOutlet.makeRecipeEntity()
        val domain = mapper.mapFromEntity(entity)

        assertEqualsData(entity, domain)
    }

    @Test
    fun `recipes entity mapper maps data from domain to entity`() {
        val domain = DataFactoryOutlet.makeRecipe()
        val entity = mapper.mapToEntity(domain)

        assertEqualsData(entity, domain)
    }

    private fun assertEqualsData(entity: RecipesEntity, domain: Recipes) {
        assertEquals(entity.id, domain.id)
        assertEquals(entity.publisher, domain.publisher)
        assertEquals(entity.siteUrl, domain.siteUrl)
        assertEquals(entity.title, domain.title)
        assertEquals(entity.sourceUrl, domain.sourceUrl)
        assertEquals(entity.imageUrl, domain.imageUrl)
        assertEquals(entity.rating, domain.rating)
        assertEquals(entity.publisherUrl, domain.publisherUrl)
        assertEquals(entity.isBookmarked, domain.isBookmarked)
        assertEquals(entity.category, domain.category)
    }
}