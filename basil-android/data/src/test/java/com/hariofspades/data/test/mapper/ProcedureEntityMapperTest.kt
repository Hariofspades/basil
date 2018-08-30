package com.hariofspades.data.test.mapper

import com.hariofspades.data.mapper.ProcedureEntityMapper
import com.hariofspades.data.model.ProcedureEntity
import com.hariofspades.data.test.data.DataFactoryOutlet
import com.hariofspades.domain.model.Procedure
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProcedureEntityMapperTest {

    private val mapper = ProcedureEntityMapper()

    @Test
    fun `procedure entity mapper maps data from entity to domain`() {
        val entity = DataFactoryOutlet.makeProcedureEntity()
        val domain = mapper.mapFromEntity(entity)

        assertEqualsData(entity, domain)
    }

    @Test
    fun `procdure entity mapper maps data from domain to entity`() {
        val domain = DataFactoryOutlet.makeProcedure()
        val entity = mapper.mapToEntity(domain)

        assertEqualsData(entity, domain)
    }

    private fun assertEqualsData(entity: ProcedureEntity, domain: Procedure) {
        assertEquals(entity.id, domain.id)
        assertEquals(entity.ingredients.size, domain.ingredients.size)
        assertEquals(entity.ingredients[0], domain.ingredients[0])
    }
}