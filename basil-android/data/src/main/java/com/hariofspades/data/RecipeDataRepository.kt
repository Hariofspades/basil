package com.hariofspades.data

import com.hariofspades.data.mapper.ProcedureEntityMapper
import com.hariofspades.data.mapper.RecipesEntityMapper
import com.hariofspades.data.repository.RecipesCache
import com.hariofspades.data.store.RecipesDataSourceFactory
import com.hariofspades.domain.model.Procedure
import com.hariofspades.domain.model.Recipes
import com.hariofspades.domain.repository.RecipeRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class RecipeDataRepository(
        private val recipesCache: RecipesCache,
        private val recipeMapper: RecipesEntityMapper,
        private val procedureMapper: ProcedureEntityMapper,
        private val recipesDataSourceFactory: RecipesDataSourceFactory
) : RecipeRepository {

    override fun getRecipies(category: String): Observable<List<Recipes>> {
        return Observable.zip(
                recipesCache.areRecipesCached().toObservable(),
                recipesCache.isRecipesCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    recipesDataSourceFactory.getDataStore(it.first, it.second).getRecipes(category)
                }
                .flatMap {
                    recipesDataSourceFactory.getCacheDataStore()
                            .saveRecipes(it)
                            .andThen(Observable.just(it))
                }
                .map {
                    it.map(recipeMapper::mapFromEntity)
                }
    }

    override fun getProcedure(id: String): Observable<Procedure> {
        return Observable.zip(
                recipesCache.areRecipesCached().toObservable(),
                recipesCache.isRecipesCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    recipesDataSourceFactory.getDataStore(it.first, it.second).getProcedure(id)
                }
                .flatMap {
                    recipesDataSourceFactory.getCacheDataStore()
                            .saveProcedure(it)
                            .andThen(Observable.just(it))
                }
                .map {
                    procedureMapper.mapFromEntity(it)
                }
    }

    override fun getBookmarkedRecipies(category: String): Observable<List<Recipes>> {
        return recipesDataSourceFactory.getCacheDataStore()
                .getBookmarkedRecipes(category)
                .map { it.map(recipeMapper::mapFromEntity) }
    }

    override fun getBookmarkedProcedure(id: String): Observable<Procedure> {
        return recipesDataSourceFactory.getCacheDataStore()
                .getBookmarkedProcedure(id)
                .map(procedureMapper::mapFromEntity)
    }

    override fun bookmarRecipie(id: String): Completable {
        return recipesDataSourceFactory.getCacheDataStore().bookmarkRecipe(id)
    }

    override fun unBookmarkRecipie(id: String): Completable {
        return recipesDataSourceFactory.getCacheDataStore().unBookmarRecipe(id)
    }

}