package com.hariofspades.domain.repository

import com.hariofspades.domain.model.Procedure
import com.hariofspades.domain.model.Recipes
import io.reactivex.Completable
import io.reactivex.Observable

interface RecipeRepository {

    fun getRecipies(category: String): Observable<List<Recipes>>

    fun getProcedure(id: String): Observable<Procedure>

    fun getBookmarkedRecipies(category: String): Observable<List<Recipes>>

    fun getBookmarkedProcedure(id: String): Observable<Procedure>

    fun bookmarRecipie(id: String): Completable

    fun unBookmarkRecipie(id: String): Completable

    fun bookmarkProcedure(id: String): Completable

    fun unBookmarkProcedure(id: String): Completable

}