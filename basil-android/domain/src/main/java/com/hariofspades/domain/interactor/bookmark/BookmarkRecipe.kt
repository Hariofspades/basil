package com.hariofspades.domain.interactor.bookmark

import com.hariofspades.domain.executor.PostExecutionThread
import com.hariofspades.domain.interactor.CompletableUseCase
import com.hariofspades.domain.interactor.ObservableUseCase
import com.hariofspades.domain.model.Procedure
import com.hariofspades.domain.repository.RecipeRepository
import io.reactivex.Completable
import io.reactivex.Observable

class BookmarkRecipe(
        private val recipeRepository: RecipeRepository,
        postExecutionThread: PostExecutionThread
) : CompletableUseCase<BookmarkRecipe.Params>(postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) {
            throw IllegalArgumentException("params cannot be null")
        }

        return recipeRepository.bookmarRecipie(params.id)
    }

    data class Params(val id: String) {
        companion object {
            fun forRecipe(id: String): Params {
                return Params(id)
            }
        }
    }
}