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
class GetBookmarkedRecipesTest {

    private lateinit var getBookmarkedRecipes: GetBookmarkedRecipes

    @Mock
    private lateinit var recipeRepository: RecipeRepository

    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getBookmarkedRecipes = GetBookmarkedRecipes(recipeRepository, postExecutionThread)
    }

    @Test
    fun `get bookmarked recipes completes`() {
        whenever(recipeRepository.getBookmarkedRecipies())
                .thenReturn(Observable.just(DataFactoryOutlet.makeRecipesList(2)))

        val testObserver = getBookmarkedRecipes.buildUseCaseObservable().test()

        testObserver.assertComplete()
    }

    @Test
    fun `get bookmarked recipes returns a value`() {
        val recipes = DataFactoryOutlet.makeRecipesList(2)

        whenever(recipeRepository.getBookmarkedRecipies()).thenReturn(Observable.just(recipes))

        val testObserver = getBookmarkedRecipes.buildUseCaseObservable().test()

        testObserver.assertValue(recipes)
    }
}