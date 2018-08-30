package com.hariofspades.domain.test.interactor.bookmark

import com.hariofspades.domain.executor.PostExecutionThread
import com.hariofspades.domain.repository.RecipeRepository
import com.hariofspades.domain.test.interactor.CompletableUseCase
import io.reactivex.Completable

class UnBookmarkProcedure(
        private val recipeRepository: RecipeRepository,
        postExecutionThread: PostExecutionThread
) : CompletableUseCase<UnBookmarkProcedure.Params>(postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) {
            throw IllegalArgumentException("params cannot be null")
        }

        return recipeRepository.unBookmarkProcedure(params.id)
    }

    data class Params(val id: String) {
        companion object {
            fun forProcedure(id: String): Params {
                return Params(id)
            }
        }
    }
}