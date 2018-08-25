package com.hariofspades.domain.interactor.bookmark

import com.hariofspades.domain.data.DataFactory
import com.hariofspades.domain.data.DataFactoryOutlet
import com.hariofspades.domain.executor.PostExecutionThread
import com.hariofspades.domain.interactor.browse.GetRecipes
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
class GetBookmarkedProcedureTest {

    private lateinit var getBookmarkedProcedure: GetBookmarkedProcedure

    @Mock
    private lateinit var recipeRepository: RecipeRepository

    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getBookmarkedProcedure = GetBookmarkedProcedure(recipeRepository, postExecutionThread)
    }

    @Test
    fun `get bookmarked procedure completes`() {
        whenever(recipeRepository.getBookmarkedProcedure(any()))
                .thenReturn(Observable.just(DataFactoryOutlet.makeProcedure()))

        val testObserver = getBookmarkedProcedure.buildUseCaseObservable(
                GetBookmarkedProcedure.Params.forProcedure(DataFactory.randomString())
        ).test()

        testObserver.assertComplete()
    }

    @Test
    fun `get bookmarked procedure returns a value`() {
        val recipes = DataFactoryOutlet.makeProcedure()

        whenever(recipeRepository.getBookmarkedProcedure(any())).
                thenReturn(Observable.just(recipes))

        val testObserver = getBookmarkedProcedure.buildUseCaseObservable(
                GetBookmarkedProcedure.Params.forProcedure(DataFactory.randomString())).test()

        testObserver.assertValue(recipes)
    }

    @Test(expected=IllegalArgumentException::class)
    fun `get bookmarked procedure throws exception`() {
        getBookmarkedProcedure.buildUseCaseObservable().test()
    }

}