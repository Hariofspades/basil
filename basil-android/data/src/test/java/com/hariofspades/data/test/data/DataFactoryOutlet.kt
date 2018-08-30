package com.hariofspades.data.test.data

import com.hariofspades.data.model.ProcedureEntity
import com.hariofspades.data.model.RecipesEntity
import com.hariofspades.domain.model.Procedure
import com.hariofspades.domain.model.Recipes

object DataFactoryOutlet {

    fun makeRecipeEntity(): RecipesEntity {
        return RecipesEntity(
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomInt(),
                DataFactory.randomString(),
                DataFactory.randomBoolean(),
                DataFactory.randomString()
        )
    }

    fun makeEntityRecipesList(count: Int): List<RecipesEntity> {
        val recipes = mutableListOf<RecipesEntity>()
        repeat(count) {
            recipes.add(makeRecipeEntity())
        }
        return recipes
    }

    fun makeProcedureEntity(): ProcedureEntity {
        return ProcedureEntity(
                DataFactory.randomString(),
                listOf(DataFactory.randomString())
        )
    }

    fun makeRecipe(): Recipes {
        return Recipes(
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomInt(),
                DataFactory.randomString(),
                DataFactory.randomBoolean(),
                DataFactory.randomString()
        )
    }

    fun makeRecipesList(count: Int): List<Recipes> {
        val recipes = mutableListOf<Recipes>()
        repeat(count) {
            recipes.add(makeRecipe())
        }
        return recipes
    }

    fun makeProcedure(): Procedure {
        return Procedure(
                DataFactory.randomString(),
                listOf(DataFactory.randomString())
        )
    }


}