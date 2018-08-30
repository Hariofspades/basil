package com.hariofspades.data.test.store

import com.hariofspades.data.model.ProcedureEntity
import com.hariofspades.data.model.RecipesEntity
import com.hariofspades.data.repository.RecipesCache
import com.hariofspades.data.store.RecipesCacheDataStore
import com.hariofspades.data.test.data.DataFactory
import com.hariofspades.data.test.data.DataFactoryOutlet
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RecipesCacheDataSourceTest {

    private val cache: RecipesCache = mock()

    private val store = RecipesCacheDataStore(cache)

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
    fun `get recipes calls cache`() {
        stubGetRecipes(Observable.just(DataFactoryOutlet.makeEntityRecipesList(2)))

        val category = DataFactory.randomString()
        store.getRecipes(category).test()
        verify(cache).getRecipes(category)
    }


    @Test
    fun `get procedure completes`() {
        stubGetProcedure(Observable.just(DataFactoryOutlet.makeProcedureEntity()))

        val testObserver = store.getProcedure(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `get procedure returns data`() {
        val data = DataFactoryOutlet.makeProcedureEntity()
        stubGetProcedure(Observable.just(data))

        val testObserver = store.getProcedure(DataFactory.randomString()).test()
        testObserver.assertValue(data)
    }

    @Test
    fun `get procedure calls cache`() {
        stubGetProcedure(Observable.just(DataFactoryOutlet.makeProcedureEntity()))

        val category = DataFactory.randomString()
        store.getProcedure(category).test()
        verify(cache).getProcedure(category)
    }

    @Test
    fun `save recipes completes`() {
        stubLastCachedTime(Completable.complete())
        stubSaveUsers(Completable.complete())

        val testObserver = store.saveRecipes(DataFactoryOutlet.makeEntityRecipesList(2)).test()
        testObserver.assertComplete()
    }

    @Test
    fun `save recipes calls cache`() {
        val data = DataFactoryOutlet.makeEntityRecipesList(2)
        stubLastCachedTime(Completable.complete())
        stubSaveUsers(Completable.complete())

        store.saveRecipes(data).test()
        verify(cache).saveRecipes(data)
    }

    @Test
    fun `save procedure completes`() {
        stubLastCachedTime(Completable.complete())
        stubSaveProcedure(Completable.complete())

        val testObserver = store.saveProcedure(DataFactoryOutlet.makeProcedureEntity()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `save procedure calls cache`() {
        val data = DataFactoryOutlet.makeProcedureEntity()
        stubLastCachedTime(Completable.complete())
        stubSaveProcedure(Completable.complete())

        store.saveProcedure(data).test()
        verify(cache).saveProcedure(data)
    }

    @Test
    fun `get bookmarked recipes completes`() {
        stubGetBookmarkedRecipes(Observable.just(DataFactoryOutlet.makeEntityRecipesList(2)))

        val testObserver = store.getBookmarkedRecipes(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `get bookmarked recipes returns data`() {
        val data = DataFactoryOutlet.makeEntityRecipesList(2)
        stubGetBookmarkedRecipes(Observable.just(data))

        val testObserver = store.getBookmarkedRecipes(DataFactory.randomString()).test()
        testObserver.assertValue(data)
    }

    @Test
    fun `get bookmarked recipes calls cache`() {
        stubGetBookmarkedRecipes(Observable.just(DataFactoryOutlet.makeEntityRecipesList(2)))

        val category = DataFactory.randomString()
        store.getBookmarkedRecipes(category).test()
        verify(cache).getBookmarkedRecipes(category)
    }

    @Test
    fun `get bookmarked procedure completes`() {
        stubGetBookmarkedProcedure(Observable.just(DataFactoryOutlet.makeProcedureEntity()))

        val testObserver = store.getBookmarkedProcedure(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `get bookmarked procedure returns data`() {
        val data = DataFactoryOutlet.makeProcedureEntity()
        stubGetBookmarkedProcedure(Observable.just(data))

        val testObserver = store.getBookmarkedProcedure(DataFactory.randomString()).test()
        testObserver.assertValue(data)
    }

    @Test
    fun `get bookmarked procedure calls cache`() {
        stubGetBookmarkedProcedure(Observable.just(DataFactoryOutlet.makeProcedureEntity()))

        val category = DataFactory.randomString()
        store.getBookmarkedProcedure(category).test()
        verify(cache).getBookmarkedProcedure(category)
    }

    @Test
    fun `clear procedure completes`() {
        stubClearProcedure(Completable.complete())

        val testObserver = store.clearProcedure().test()
        testObserver.assertComplete()
    }

    @Test
    fun `clear procedure calls cache`() {
        stubClearProcedure(Completable.complete())

        store.clearProcedure().test()
        verify(cache).clearProcedures()
    }

    @Test
    fun `clear recipes completes`() {
        stubClearRecipes(Completable.complete())

        val testObserver = store.clearRecipes().test()
        testObserver.assertComplete()
    }

    @Test
    fun `clear recipes calls cache`() {
        stubClearRecipes(Completable.complete())

        store.clearRecipes().test()
        verify(cache).clearRecipes()
    }

    @Test
    fun `bookmark recipes completes`() {
        stubBookmarkRecipe(Completable.complete())

        val testObserver = store.bookmarkRecipe(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `bookmark recipes calls cache`() {
        stubBookmarkRecipe(Completable.complete())

        val category = DataFactory.randomString()
        store.bookmarkRecipe(category).test()
        verify(cache).bookmarkRecipe(category)
    }

    @Test
    fun `un bookmark recipes completes`() {
        stubUnBookmarkRecipe(Completable.complete())

        val testObserver = store.unBookmarRecipe(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `un bookmark recipes calls cache`() {
        stubUnBookmarkRecipe(Completable.complete())

        val category = DataFactory.randomString()
        store.unBookmarRecipe(category).test()
        verify(cache).unBookmarRecipe(category)
    }

    @Test
    fun `bookmark procedure completes`() {
        stubBookmarkProcedure(Completable.complete())

        val testObserver = store.bookmarkProcedure(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `bookmark procedure calls cache`() {
        stubBookmarkProcedure(Completable.complete())

        val category = DataFactory.randomString()
        store.bookmarkProcedure(category).test()
        verify(cache).bookmarkProcedure(category)
    }

    @Test
    fun `un bookmark procedure completes`() {
        stubUnBookmarkProcedure(Completable.complete())

        val testObserver = store.unBookmarProcedure(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `un bookmark procedure calls cache`() {
        stubUnBookmarkProcedure(Completable.complete())

        val category = DataFactory.randomString()
        store.unBookmarProcedure(category).test()
        verify(cache).unBookmarProcedure(category)
    }

    private fun stubClearProcedure(completable: Completable) {
        whenever(cache.clearProcedures()).thenReturn(completable)
    }

    private fun stubClearRecipes(completable: Completable) {
        whenever(cache.clearRecipes()).thenReturn(completable)
    }

    private fun stubGetRecipes(observable: Observable<List<RecipesEntity>>) {
        whenever(cache.getRecipes(any())).thenReturn(observable)
    }

    private fun stubGetProcedure(observable: Observable<ProcedureEntity>) {
        whenever(cache.getProcedure(any())).thenReturn(observable)
    }

    private fun stubLastCachedTime(completable: Completable) {
        whenever(cache.setLastCacheTime(any())).thenReturn(completable)
    }

    private fun stubSaveUsers(completable: Completable) {
        whenever(cache.saveRecipes(any())).thenReturn(completable)
    }

    private fun stubSaveProcedure(completable: Completable) {
        whenever(cache.saveProcedure(any())).thenReturn(completable)
    }

    private fun stubGetBookmarkedRecipes(observable: Observable<List<RecipesEntity>>) {
        whenever(cache.getBookmarkedRecipes(any())).thenReturn(observable)
    }

    private fun stubGetBookmarkedProcedure(observable: Observable<ProcedureEntity>) {
        whenever(cache.getBookmarkedProcedure(any())).thenReturn(observable)
    }

    private fun stubBookmarkRecipe(completable: Completable) {
        whenever(cache.bookmarkRecipe(any())).thenReturn(completable)
    }

    private fun stubUnBookmarkRecipe(completable: Completable) {
        whenever(cache.unBookmarRecipe(any())).thenReturn(completable)
    }

    private fun stubBookmarkProcedure(completable: Completable) {
        whenever(cache.bookmarkProcedure(any())).thenReturn(completable)
    }

    private fun stubUnBookmarkProcedure(completable: Completable) {
        whenever(cache.unBookmarProcedure(any())).thenReturn(completable)
    }
}