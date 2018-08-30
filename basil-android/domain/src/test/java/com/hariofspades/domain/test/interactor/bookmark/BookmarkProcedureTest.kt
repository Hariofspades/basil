package com.hariofspades.domain.test.interactor.bookmark

import com.hariofspades.domain.executor.PostExecutionThread
import com.hariofspades.domain.repository.RecipeRepository
import com.hariofspades.domain.test.data.DataFactory
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
class BookmarkProcedureTest {

    private lateinit var bookmarkProcedure: BookmarkProcedure

    @Mock
    private lateinit var recipeRepository: RecipeRepository

    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        bookmarkProcedure = BookmarkProcedure(recipeRepository, postExecutionThread)
    }

    @Test
    fun `bookmark procedure completes`() {
        whenever(recipeRepository.bookmarkProcedure(any()))
                .thenReturn(Completable.complete())

        val testObserver = bookmarkProcedure.buildUseCaseCompletable(
                BookmarkProcedure.Params.forProcedure(DataFactory.randomString())).test()

        testObserver.assertComplete()
    }

    @Test(expected=IllegalArgumentException::class)
    fun `bookmark procedure throws exception`() {
        bookmarkProcedure.buildUseCaseCompletable().test()
    }
}