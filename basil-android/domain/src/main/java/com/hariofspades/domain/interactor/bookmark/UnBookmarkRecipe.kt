package com.hariofspades.domain.interactor.bookmark

import com.hariofspades.domain.executor.PostExecutionThread
import com.hariofspades.domain.interactor.CompletableUseCase
import com.hariofspades.domain.repository.RecipeRepository
import io.reactivex.Completable

class UnBookmarkRecipe(
        private val recipeRepository: RecipeRepository,
        postExecutionThread: PostExecutionThread
) : CompletableUseCase<UnBookmarkRecipe.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: UnBookmarkRecipe.Params?): Completable {
        if (params == null) {
            throw IllegalArgumentException("params cannot be null")
        }

        return recipeRepository.unBookmarkRecipie(params.id)
    }

    data class Params(val id: String) {
        companion object {
            fun forRecipe(id: String): Params {
                return Params(id)
            }
        }
    }
}