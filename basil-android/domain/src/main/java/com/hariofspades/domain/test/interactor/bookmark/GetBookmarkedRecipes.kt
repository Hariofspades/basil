package com.hariofspades.domain.test.interactor.bookmark

import com.hariofspades.domain.executor.PostExecutionThread
import com.hariofspades.domain.test.interactor.ObservableUseCase
import com.hariofspades.domain.model.Recipes
import com.hariofspades.domain.repository.RecipeRepository
import io.reactivex.Observable

class GetBookmarkedRecipes(
        private val recipeRepository: RecipeRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Recipes>, GetBookmarkedRecipes.Params>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<List<Recipes>> {
        if (params == null) {
            throw IllegalArgumentException("params cannot be null")
        }

        return recipeRepository.getBookmarkedRecipies(params.category)
    }

    data class Params(val category: String) {
        companion object {
            fun forRecipes(category: String): Params {
                return Params(category)
            }
        }
    }
}