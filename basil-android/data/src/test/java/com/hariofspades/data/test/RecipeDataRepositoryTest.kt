package com.hariofspades.data.test

import com.hariofspades.data.RecipeDataRepository
import com.hariofspades.data.mapper.ProcedureEntityMapper
import com.hariofspades.data.mapper.RecipesEntityMapper
import com.hariofspades.data.model.ProcedureEntity
import com.hariofspades.data.model.RecipesEntity
import com.hariofspades.data.repository.RecipesCache
import com.hariofspades.data.repository.RecipesDataSource
import com.hariofspades.data.store.RecipesDataSourceFactory
import com.hariofspades.data.test.data.DataFactory
import com.hariofspades.data.test.data.DataFactoryOutlet
import com.hariofspades.domain.model.Procedure
import com.hariofspades.domain.model.Recipes
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RecipeDataRepositoryTest {

    private val recipesCache: RecipesCache = mock()
    private val recipeMapper: RecipesEntityMapper = mock()
    private val procedureMapper: ProcedureEntityMapper = mock()
    private val recipesDataSourceFactory: RecipesDataSourceFactory = mock()

    private val recipesDataStore: RecipesDataSource = mock()

    private val dataRepository = RecipeDataRepository(
            recipesCache,
            recipeMapper,
            procedureMapper,
            recipesDataSourceFactory
    )

    @Before
    fun setup() {
        stubGetDataStore()
        stubGetCachedDataStore()
        stubAreUseresCached(Single.just(false))
        stubIsProjectCacheExpired(Single.just(false))
        stubSaveRecipes(Completable.complete())
        stubSaveProcedures(Completable.complete())
    }

    @Test
    fun `get recipes completes`() {
        stubGetRecipes(Observable.just(DataFactoryOutlet.makeEntityRecipesList(2)))
        stubRecipesMapper(any(), DataFactoryOutlet.makeRecipeEntity())

        val testObserver = dataRepository.getRecipies(
                DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `get recipes returns data`() {
        val entity = DataFactoryOutlet.makeRecipeEntity()
        val model = DataFactoryOutlet.makeRecipe()

        stubGetRecipes(Observable.just(listOf(entity)))
        stubRecipesMapper(model, entity)

        val testObserver = dataRepository.getRecipies(DataFactory.randomString()).test()
        testObserver.assertValue(listOf(model))
    }

    @Test
    fun `get procedure completes`() {
        stubGetProcedure(Observable.just(DataFactoryOutlet.makeProcedureEntity()))
        stubProcedurMapper(DataFactoryOutlet.makeProcedure(), any())

        val testObserver = dataRepository.getProcedure(
                DataFactory.randomString()).test()

        testObserver.assertComplete()
    }

    @Test
    fun `get procedure returns data`() {
        val entity = DataFactoryOutlet.makeProcedureEntity()
        val model = DataFactoryOutlet.makeProcedure()

        stubGetProcedure(Observable.just(entity))
        stubProcedurMapper(model, entity)

        val testObserver = dataRepository.getProcedure(DataFactory.randomString()).test()
        testObserver.assertValue(model)
    }

    @Test
    fun `get bookmarked recipes completes`() {
        stubGetBookmarkedRecipes(Observable.just(listOf(DataFactoryOutlet.makeRecipeEntity())))
        stubRecipesMapper(any(), DataFactoryOutlet.makeRecipeEntity())

        val testObserver = dataRepository.getBookmarkedRecipies(
                DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `get bookmarked recipes returns data`() {
        val entity = DataFactoryOutlet.makeRecipeEntity()
        val model = DataFactoryOutlet.makeRecipe()

        stubGetBookmarkedRecipes(Observable.just(listOf(entity)))
        stubRecipesMapper(model, entity)

        val testObserver = dataRepository.getBookmarkedRecipies(DataFactory.randomString()).test()
        testObserver.assertValue(listOf(model))
    }

    @Test
    fun `get bookmarked procedure completes`() {
        stubGetBookmarkedProcedure(Observable.just(DataFactoryOutlet.makeProcedureEntity()))
        stubProcedurMapper(DataFactoryOutlet.makeProcedure(), any())

        val testObserver = dataRepository.getBookmarkedProcedure(
                DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `get bookmarked procedure returns data`() {
        val entity = DataFactoryOutlet.makeProcedureEntity()
        val model = DataFactoryOutlet.makeProcedure()

        stubGetBookmarkedProcedure(Observable.just(entity))
        stubProcedurMapper(model, entity)

        val testObserver = dataRepository.getBookmarkedProcedure(DataFactory.randomString()).test()
        testObserver.assertValue(model)
    }

    @Test
    fun `bookmark recipe completes`() {
        stubBookmarkRecipe(Completable.complete())

        val testObserver = dataRepository.bookmarRecipie(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `bookmark procedure completes`() {
        stubBookmarProcedure(Completable.complete())

        val testObserver = dataRepository.bookmarkProcedure(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `unbookmark recipe completes`() {
        stubUnBookmarkRecipe(Completable.complete())

        val testObserver = dataRepository.unBookmarkRecipie(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun `unbookmark procedure completes`() {
        stubUnBookmarProcedure(Completable.complete())

        val testObserver = dataRepository.unBookmarkProcedure(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    private fun stubBookmarkRecipe(complete: Completable) {
        whenever(recipesDataStore.bookmarkRecipe(any())).thenReturn(complete)
    }

    private fun stubBookmarProcedure(complete: Completable) {
        whenever(recipesDataStore.bookmarkProcedure(any())).thenReturn(complete)
    }

    private fun stubUnBookmarkRecipe(complete: Completable) {
        whenever(recipesDataStore.unBookmarRecipe(any())).thenReturn(complete)
    }

    private fun stubUnBookmarProcedure(complete: Completable) {
        whenever(recipesDataStore.unBookmarProcedure(any())).thenReturn(complete)
    }

    private fun stubGetBookmarkedProcedure(observable: Observable<ProcedureEntity>) {
        whenever(recipesDataStore.getBookmarkedProcedure(any())).thenReturn(observable)
    }

    private fun stubGetBookmarkedRecipes(observable: Observable<List<RecipesEntity>>) {
        whenever(recipesDataStore.getBookmarkedRecipes(any())).thenReturn(observable)
    }

    private fun stubProcedurMapper(model: Procedure, entity: ProcedureEntity) {
        whenever(procedureMapper.mapFromEntity(entity)).thenReturn(model)
    }

    private fun stubGetProcedure(observable: Observable<ProcedureEntity>) {
        whenever(recipesDataStore.getProcedure(any())).thenReturn(observable)
    }

    private fun stubRecipesMapper(model: Recipes, entity: RecipesEntity) {
        whenever(recipeMapper.mapFromEntity(entity)).thenReturn(model)
    }

    private fun stubGetRecipes(observable: Observable<List<RecipesEntity>>) {
        whenever(recipesDataStore.getRecipes(any())).thenReturn(observable)
    }

    private fun stubSaveProcedures(complete: Completable) {
        whenever(recipesDataStore.saveProcedure(any()))
                .thenReturn(complete)
    }

    private fun stubSaveRecipes(complete: Completable) {
        whenever(recipesDataStore.saveRecipes(any()))
                .thenReturn(complete)
    }

    private fun stubGetDataStore() {
        whenever(recipesDataSourceFactory.getDataStore(any(), any())).thenReturn(recipesDataStore)
    }

    private fun stubGetCachedDataStore() {
        whenever(recipesDataSourceFactory.getCacheDataStore()).thenReturn(recipesDataStore)
    }

    private fun stubAreUseresCached(boolean: Single<Boolean>) {
        whenever(recipesCache.areRecipesCached()).thenReturn(boolean)
    }

    private fun stubIsProjectCacheExpired(boolean: Single<Boolean>) {
        whenever(recipesCache.isRecipesCacheExpired()).thenReturn(boolean)
    }
}