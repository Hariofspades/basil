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
class UnBookmarkProcedureTest {

    private lateinit var unBookmarkProcedure: UnBookmarkProcedure

    @Mock
    private lateinit var recipeRepository: RecipeRepository

    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        unBookmarkProcedure = UnBookmarkProcedure(recipeRepository, postExecutionThread)
    }

    @Test
    fun `un bookmark procedure completes`() {
        whenever(recipeRepository.unBookmarkProcedure(any()))
                .thenReturn(Completable.complete())

        val testObserver = unBookmarkProcedure.buildUseCaseCompletable(
                UnBookmarkProcedure.Params.forProcedure(DataFactory.randomString())).test()

        testObserver.assertComplete()
    }

    @Test(expected=IllegalArgumentException::class)
    fun `un bookmark recipe throws exception`() {
        unBookmarkProcedure.buildUseCaseCompletable().test()
    }
}