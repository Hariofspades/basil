package com.hariofspades.data.store

import com.hariofspades.data.model.ProcedureEntity
import com.hariofspades.data.model.RecipesEntity
import com.hariofspades.data.repository.RecipesCache
import com.hariofspades.data.repository.RecipesDataSource
import io.reactivex.Completable
import io.reactivex.Observable

class RecipesCacheDataStore(

        private val recipesCache: RecipesCache

) : RecipesDataSource {

    override fun saveProcedure(procedure: ProcedureEntity): Completable {
        return recipesCache.saveProcedure(procedure)
    }

    override fun clearProcedure(): Completable {
        return recipesCache.clearProcedures()
    }

    override fun getRecipes(category: String): Observable<List<RecipesEntity>> {
        return recipesCache.getRecipes(category)
    }

    override fun getProcedure(id: String): Observable<ProcedureEntity> {
        return recipesCache.getProcedure(id)
    }

    override fun saveRecipes(list: List<RecipesEntity>): Completable {
        return recipesCache.saveRecipes(list)
    }

    override fun clearRecipes(): Completable {
        return recipesCache.clearRecipes()
    }

    override fun getBookmarkedRecipes(category: String): Observable<List<RecipesEntity>> {
        return recipesCache.getBookmarkedRecipes(category)
    }

    override fun getBookmarkedProcedure(id: String): Observable<ProcedureEntity> {
        return recipesCache.getBookmarkedProcedure(id)
    }

    override fun bookmarkRecipe(id: String): Completable {
        return recipesCache.bookmarkRecipe(id)
    }

    override fun unBookmarRecipe(id: String): Completable {
        return recipesCache.unBookmarRecipe(id)
    }

    override fun bookmarkProcedure(id: String): Completable {
        return recipesCache.bookmarkProcedure(id)
    }

    override fun unBookmarProcedure(id: String): Completable {
        return recipesCache.unBookmarProcedure(id)
    }

}