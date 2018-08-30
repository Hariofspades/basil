package com.hariofspades.domain.test.interactor.bookmark

import com.hariofspades.domain.test.data.DataFactory
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
class BookmarkRecipeTest {

    private lateinit var bookmarkRecipe: BookmarkRecipe

    @Mock
    private lateinit var recipeRepository: RecipeRepository

    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        bookmarkRecipe = BookmarkRecipe(recipeRepository, postExecutionThread)
    }

    @Test
    fun `bookmark recipe completes`() {
        whenever(recipeRepository.bookmarRecipie(any()))
                .thenReturn(Completable.complete())

        val testObserver = bookmarkRecipe.buildUseCaseCompletable(
                BookmarkRecipe.Params.forRecipe(DataFactory.randomString())).test()

        testObserver.assertComplete()
    }

    @Test(expected=IllegalArgumentException::class)
    fun `bookmark recipe throws exception`() {
        bookmarkRecipe.buildUseCaseCompletable().test()
    }

}