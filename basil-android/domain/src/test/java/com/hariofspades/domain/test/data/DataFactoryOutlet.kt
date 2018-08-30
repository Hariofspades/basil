package com.hariofspades.domain.test.data

import com.hariofspades.domain.model.Procedure
import com.hariofspades.domain.model.Recipes

object DataFactoryOutlet {

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