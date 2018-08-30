package com.hariofspades.data.store

import com.hariofspades.data.model.ProcedureEntity
import com.hariofspades.data.model.RecipesEntity
import com.hariofspades.data.repository.RecipesDataSource
import com.hariofspades.data.repository.RecipesRemote
import io.reactivex.Completable
import io.reactivex.Observable

class RecipesRemoteDataSource(
        private val recipesRemote: RecipesRemote
) : RecipesDataSource {

    override fun getRecipes(category: String): Observable<List<RecipesEntity>> {
        return recipesRemote.getRecipes(category)
    }

    override fun getProcedure(id: String): Observable<ProcedureEntity> {
       return recipesRemote.getProcedure(id)
    }

    override fun saveRecipes(list: List<RecipesEntity>): Completable {
        throw UnsupportedOperationException("Saving recipes is not supported here..")
    }

    override fun clearRecipes(): Completable {
        throw UnsupportedOperationException("Clearing recipes is not supported here..")
    }

    override fun getBookmarkedRecipes(category: String): Observable<List<RecipesEntity>> {
        throw UnsupportedOperationException("bookmarking recipes operation not allowed..")
    }

    override fun getBookmarkedProcedure(id: String): Observable<ProcedureEntity> {
        throw UnsupportedOperationException("bookmaring procedure operation not allowed..")
    }

    override fun bookmarkRecipe(id: String): Completable {
        throw UnsupportedOperationException("bookmaring recipe operation not allowed..")
    }

    override fun unBookmarRecipe(id: String): Completable {
        throw UnsupportedOperationException("un bookmaring recipe operation not allowed..")
    }

    override fun saveProcedure(procedure: ProcedureEntity): Completable {
        throw UnsupportedOperationException("Saving procedure is not supported here..")
    }

    override fun clearProcedure(): Completable {
        throw UnsupportedOperationException("clearing procedure is not supported here..")
    }

    override fun bookmarkProcedure(id: String): Completable {
        throw UnsupportedOperationException("bookmaring procedure operation not allowed..")
    }

    override fun unBookmarProcedure(id: String): Completable {
        throw UnsupportedOperationException("un bookmaring procedure operation not allowed..")
    }

}