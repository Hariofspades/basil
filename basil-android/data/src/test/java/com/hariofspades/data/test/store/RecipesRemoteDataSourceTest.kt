package com.hariofspades.data.test.store

import com.hariofspades.data.model.ProcedureEntity
import com.hariofspades.data.model.RecipesEntity
import com.hariofspades.data.repository.RecipesRemote
import com.hariofspades.data.store.RecipesRemoteDataSource
import com.hariofspades.data.test.data.DataFactory
import com.hariofspades.data.test.data.DataFactoryOutlet
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RecipesRemoteDataSourceTest {

    private val remote: RecipesRemote = mock()

    private val store = RecipesRemoteDataSource(remote)

    @Test
    fun `get recipes completes`() {
        stubGetRecipes(Observable.just(DataFactoryOutlet.makeEntityRecipesList(2)))

        val testObserver = store.getRecipes(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `get recipes returns data`() {
        val data = DataFactoryOutlet.makeEntityRecipesList(2)
        stubGetRecipes(Observable.just(data))

        val testObserver = store.getRecipes(DataFactory.randomString()).test()
        testObserver.assertValue(data)

    }

    @Test
    fun `get procedures completes`() {
        stubGetProcedure(Observable.just(DataFactoryOutlet.makeProcedureEntity()))

        val testObserver = store.getProcedure(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `get procedures returs data`() {
        val data = DataFactoryOutlet.makeProcedureEntity()
        stubGetProcedure(Observable.just(data))

        val testObserver = store.getProcedure(DataFactory.randomString()).test()
        testObserver.assertValue(data)
    }

    @Test
    fun `get recipes calls remote`() {
        stubGetRecipes(Observable.just(DataFactoryOutlet.makeEntityRecipesList(2)))

        val category = DataFactory.randomString()
        store.getRecipes(category).test()
        verify(remote).getRecipes(category)
    }

    @Test
    fun `get proedure calls remote`() {
        stubGetProcedure(Observable.just(DataFactoryOutlet.makeProcedureEntity()))

        val id = DataFactory.randomString()
        store.getProcedure(id).test()
        verify(remote).getProcedure(id)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `save recipes reports exception`() {
        store.saveRecipes(DataFactoryOutlet.makeEntityRecipesList(2)).test().assertComplete()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `save procedure reports exception`() {
        store.saveProcedure(DataFactoryOutlet.makeProcedureEntity()).test().assertComplete()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `clear recipes reports exception`() {
        store.clearRecipes().test().assertComplete()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `clear procedure reports exception`() {
        store.clearProcedure().test().assertComplete()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `get bookmarked recipes reports exception`() {
        store.getBookmarkedRecipes(DataFactory.randomString()).test().assertComplete()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `get bookmarked procedure reports exception`() {
        store.getBookmarkedProcedure(DataFactory.randomString()).test().assertComplete()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `bookmark recipe reports exception`() {
        store.bookmarkRecipe(DataFactory.randomString()).test().assertComplete()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `un bookmark recipe reports exception`() {
        store.unBookmarRecipe(DataFactory.randomString()).test().assertComplete()
    }

    private fun stubGetRecipes(observable: Observable<List<RecipesEntity>>) {
        whenever(remote.getRecipes(any())).thenReturn(observable)
    }

    private fun stubGetProcedure(observable: Observable<ProcedureEntity>) {
        whenever(remote.getProcedure(any())).thenReturn(observable)
    }
}