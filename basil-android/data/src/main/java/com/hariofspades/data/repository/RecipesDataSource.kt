package com.hariofspades.data.repository

import com.hariofspades.data.model.ProcedureEntity
import com.hariofspades.data.model.RecipesEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface RecipesDataSource {

    fun getRecipes(category: String): Observable<List<RecipesEntity>>

    fun getProcedure(id: String): Observable<ProcedureEntity>

    fun saveRecipes(list: List<RecipesEntity>): Completable

    fun saveProcedure(procedure: ProcedureEntity): Completable

    fun clearRecipes(): Completable

    fun clearProcedure(): Completable

    fun getBookmarkedRecipes(category: String): Observable<List<RecipesEntity>>

    fun getBookmarkedProcedure(id: String): Observable<ProcedureEntity>

    fun bookmarkRecipe(id: String): Completable

    fun unBookmarRecipe(id: String): Completable

}