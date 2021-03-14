package com.joaoibarra.broker.ui.property

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.joaoibarra.broker.data.db.property.Property
import com.joaoibarra.broker.data.db.property.PropertyMock
import com.joaoibarra.broker.data.db.property.PropertyRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PropertyListViewModelTest {
    @MockK
    private lateinit var propertyListViewModel: PropertyListViewModel

    @MockK
    lateinit var propertyRepository: PropertyRepository

    @MockK
    private lateinit var propertyObserver: Observer<List<Property>>

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getRestaurants is called with valid list, then observer is updated with success`() =
        runBlocking {
            propertyRepository = mockk {
                coEvery { getProperties() } returns flow { emit(PropertyMock.properties) }
            }
            propertyListViewModel = PropertyListViewModel(propertyRepository)
            propertyListViewModel.properties.observeForever(propertyObserver)
            coVerify { propertyRepository.getProperties() }
            verify { propertyObserver.onChanged(PropertyMock.properties) }
        }

    @Test
    fun `when getRestaurants is called with valid list, then observer is updated with empty list`() =
        runBlocking {
            propertyRepository = mockk {
                coEvery { getProperties() } returns flow { emit(emptyList<Property>()) }
            }
            propertyListViewModel = PropertyListViewModel(propertyRepository)
            propertyListViewModel.properties.observeForever(propertyObserver)
            coVerify { propertyRepository.getProperties() }
            verify { propertyObserver.onChanged(emptyList()) }
        }
}