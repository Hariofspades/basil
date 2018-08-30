package com.hariofspades.data.test.store

import com.hariofspades.data.repository.RecipesRemote
import com.hariofspades.data.store.RecipesCacheDataStore
import com.hariofspades.data.store.RecipesDataSourceFactory
import com.hariofspades.data.store.RecipesRemoteDataSource
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RecipesDataSourceFactoryTest {

    private val cacheStore: RecipesCacheDataStore = mock()
    private val remoteStore: RecipesRemoteDataSource = mock()

    private val dataSourceFactory = RecipesDataSourceFactory(cacheStore, remoteStore)

    @Test
    fun `get data store returns cache when data is cached and if cache not expired`() {
        assertEquals(cacheStore, dataSourceFactory.getDataStore(true, false))
    }

    @Test
    fun `get data store returns remote when data is cached and if cache has expired`() {
        assertEquals(remoteStore, dataSourceFactory.getDataStore(true, true))
    }

    @Test
    fun `get data store returns remote when data is not cached `() {
        assertEquals(remoteStore, dataSourceFactory.getDataStore(false, false))
    }

    @Test
    fun `get cache data store returns cache`() {
        assertEquals(cacheStore, dataSourceFactory.getCacheDataStore())
    }
}