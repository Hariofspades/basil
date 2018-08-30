package com.hariofspades.data.store

import com.hariofspades.data.repository.RecipesDataSource

open class RecipesDataSourceFactory(
        private val recipesCacheDataStore: RecipesCacheDataStore,
        private val recipesRemoteDataSource: RecipesRemoteDataSource) {

    open fun getDataStore(userCached: Boolean, userCacheExpired: Boolean): RecipesDataSource {
        return if (userCached && !userCacheExpired) {
            recipesCacheDataStore
        } else {
            recipesRemoteDataSource
        }
    }

    open fun getCacheDataStore(): RecipesDataSource {
        return recipesCacheDataStore
    }

}