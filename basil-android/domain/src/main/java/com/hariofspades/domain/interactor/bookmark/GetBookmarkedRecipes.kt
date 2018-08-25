package com.hariofspades.domain.interactor.bookmark

import com.hariofspades.domain.executor.PostExecutionThread
import com.hariofspades.domain.interactor.ObservableUseCase
import com.hariofspades.domain.model.Recipes
import com.hariofspades.domain.repository.RecipeRepository
import io.reactivex.Observable

class GetBookmarkedRecipes(
        private val recipeRepository: RecipeRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Recipes>, Nothing>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Recipes>> {
        return recipeRepository.getBookmarkedRecipies()
    }
}