package com.hariofspades.data.repository

import com.hariofspades.data.model.ProcedureEntity
import com.hariofspades.data.model.RecipesEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface RecipesCache {

    fun getRecipes(category: String): Observable<List<RecipesEntity>>

    fun getProcedure(id: String): Observable<ProcedureEntity>

    fun saveRecipes(list: List<RecipesEntity>): Completable

    fun saveProcedure(list: ProcedureEntity): Completable

    fun clearRecipes(): Completable

    fun clearProcedures(): Completable

    fun getBookmarkedRecipes(category: String): Observable<List<RecipesEntity>>

    fun getBookmarkedProcedure(id: String): Observable<ProcedureEntity>

    fun bookmarkRecipe(id: String): Completable

    fun unBookmarRecipe(id: String): Completable

    fun areRecipesCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isRecipesCacheExpired(): Single<Boolean>
}