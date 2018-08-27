package com.hariofspades.data.repository

import com.hariofspades.data.model.ProcedureEntity
import com.hariofspades.data.model.RecipesEntity
import io.reactivex.Observable

interface RecipesRemote {

    fun getRecipes(category: String): Observable<List<RecipesEntity>>

    fun getProcedure(id: String): Observable<ProcedureEntity>

}