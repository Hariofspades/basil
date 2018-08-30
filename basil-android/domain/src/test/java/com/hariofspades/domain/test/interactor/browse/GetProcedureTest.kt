package com.hariofspades.domain.test.interactor.browse

import com.hariofspades.domain.test.data.DataFactory
import com.hariofspades.domain.test.data.DataFactoryOutlet
import com.hariofspades.domain.executor.PostExecutionThread
import com.hariofspades.domain.repository.RecipeRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GetProcedureTest {

    private lateinit var getProcedure: GetProcedure

    @Mock
    private lateinit var recipeRepository: RecipeRepository

    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProcedure = GetProcedure(recipeRepository, postExecutionThread)
    }

    @Test
    fun `get procedure completes`() {
        whenever(recipeRepository.getProcedure(any()))
                .thenReturn(Observable.just(DataFactoryOutlet.makeProcedure()))

        val testObserver = getProcedure.buildUseCaseObservable(
                GetProcedure.Params.forProcedure(DataFactory.randomString())).test()

        testObserver.assertComplete()
    }

    @Test
    fun `get procedure returns a value`() {
        val recipes = DataFactoryOutlet.makeProcedure()

        whenever(recipeRepository.getProcedure(any())).thenReturn(Observable.just(recipes))

        val testObserver = getProcedure.buildUseCaseObservable(
                GetProcedure.Params.forProcedure(DataFactory.randomString())).test()

        testObserver.assertValue(recipes)
    }

    @Test(expected=IllegalArgumentException::class)
    fun `get procedure throws exception`() {
        getProcedure.buildUseCaseObservable().test()
    }
}