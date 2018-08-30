package com.hariofspades.domain.test.interactor.bookmark

import com.hariofspades.domain.executor.PostExecutionThread
import com.hariofspades.domain.test.interactor.ObservableUseCase
import com.hariofspades.domain.model.Procedure
import com.hariofspades.domain.repository.RecipeRepository
import io.reactivex.Observable

class GetBookmarkedProcedure(
        private val recipeRepository: RecipeRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<Procedure, GetBookmarkedProcedure.Params>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<Procedure> {
        if (params == null) {
            throw IllegalArgumentException("params cannot be null")
        }

        return recipeRepository.getBookmarkedProcedure(params.id)
    }

    data class Params(val id: String) {
        companion object {
            fun forProcedure(id: String): Params {
                return Params(id)
            }
        }
    }
}