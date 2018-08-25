package com.hariofspades.domain.interactor.bookmark

import com.hariofspades.domain.data.DataFactory
import com.hariofspades.domain.executor.PostExecutionThread
import com.hariofspades.domain.repository.RecipeRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UnBookmarkRecipeTest {

    private lateinit var unBookmarkRecipe: UnBookmarkRecipe

    @Mock
    private lateinit var recipeRepository: RecipeRepository

    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        unBookmarkRecipe = UnBookmarkRecipe(recipeRepository, postExecutionThread)
    }

    @Test
    fun `un bookmark recipe completes`() {
        whenever(recipeRepository.unBookmarkRecipie(any()))
                .thenReturn(Completable.complete())

        val testObserver = unBookmarkRecipe.buildUseCaseCompletable(
                UnBookmarkRecipe.Params.forRecipe(DataFactory.randomString())).test()

        testObserver.assertComplete()
    }

    @Test(expected=IllegalArgumentException::class)
    fun `un bookmark recipe throws exception`() {
        unBookmarkRecipe.buildUseCaseCompletable().test()
    }
}