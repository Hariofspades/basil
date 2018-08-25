package com.hariofspades.domain.interactor.browse

import com.hariofspades.domain.executor.PostExecutionThread
import com.hariofspades.domain.interactor.ObservableUseCase
import com.hariofspades.domain.model.Recipes
import com.hariofspades.domain.repository.RecipeRepository
import io.reactivex.Observable

class GetRecipes(
        private val recipeRepository: RecipeRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Recipes>, GetRecipes.Params>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<List<Recipes>> {
        if (params == null) {
            throw IllegalArgumentException("params cannot be null")
        }
        return recipeRepository.getRecipies(params.category)
    }

    data class Params(val category: String) {
        companion object {
            fun forRecipies(category: String): Params {
                return Params(category)
            }
        }
    }
}