package com.hariofspades.data.mapper

import com.hariofspades.data.model.ProcedureEntity
import com.hariofspades.domain.model.Procedure

class ProcedureEntityMapper : EntityMapper<ProcedureEntity, Procedure> {

    override fun mapFromEntity(entity: ProcedureEntity): Procedure {
        return Procedure(
                entity.id,
                entity.ingredients
        )
    }

    override fun mapToEntity(domain: Procedure): ProcedureEntity {
        return ProcedureEntity(
                domain.id,
                domain.ingredients
        )
    }
}