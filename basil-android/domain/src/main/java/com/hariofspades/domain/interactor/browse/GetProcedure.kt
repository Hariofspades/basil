package com.hariofspades.domain.interactor.browse

import com.hariofspades.domain.executor.PostExecutionThread
import com.hariofspades.domain.interactor.ObservableUseCase
import com.hariofspades.domain.model.Procedure
import com.hariofspades.domain.repository.RecipeRepository
import io.reactivex.Observable

class GetProcedure(
        private val recipeRepository: RecipeRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<Procedure, GetProcedure.Params>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: GetProcedure.Params?): Observable<Procedure> {
        if (params == null) {
            throw IllegalArgumentException("params cannot be null")
        }

        return recipeRepository.getProcedure(params.id)
    }

    data class Params(val id: String) {
        companion object {
            fun forProcedure(id: String): Params {
                return Params(id)
            }
        }
    }
}