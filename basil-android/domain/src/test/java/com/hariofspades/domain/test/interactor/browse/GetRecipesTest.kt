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
class GetRecipesTest {

    private lateinit var getRecipes: GetRecipes

    @Mock
    private lateinit var recipeRepository: RecipeRepository

    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getRecipes = GetRecipes(recipeRepository, postExecutionThread)
    }

    @Test
    fun `get recipes completes`() {
        whenever(recipeRepository.getRecipies(any()))
                .thenReturn(Observable.just(DataFactoryOutlet.makeRecipesList(2)))

        val testObserver = getRecipes.buildUseCaseObservable(
                GetRecipes.Params.forRecipies(DataFactory.randomString())).test()

        testObserver.assertComplete()
    }

    @Test
    fun `get recipes returns a value`() {
        val recipes = DataFactoryOutlet.makeRecipesList(2)

        whenever(recipeRepository.getRecipies(any())).thenReturn(Observable.just(recipes))

        val testObserver = getRecipes.buildUseCaseObservable(
                GetRecipes.Params.forRecipies(DataFactory.randomString())).test()

        testObserver.assertValue(recipes)
    }

    @Test(expected=IllegalArgumentException::class)
    fun `get recipe throws exception`() {
        getRecipes.buildUseCaseObservable().test()
    }

}