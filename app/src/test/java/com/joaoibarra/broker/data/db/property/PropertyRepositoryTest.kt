package com.joaoibarra.broker.data.db.property

import com.joaoibarra.broker.data.remote.BrokerApi
import com.joaoibarra.broker.data.remote.domain.property.PropertyListResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import retrofit2.HttpException
import retrofit2.Response

class PropertyRepositoryTest {
    @MockK
    lateinit var brokerApi: BrokerApi

    @MockK
    lateinit var propertyRepository: PropertyRepository

    private val propertiessResponseSuccess = Response.success(PropertyMock.propertyListResponse)

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `when api returns 401 should return empty list`() =
            runBlocking {
                val restaurantsResponseError = Response.error<PropertyListResponse>(
                        401,
                        mockk<ResponseBody>() {
                            every { contentType() } returns "application/json; charset=UTF-8".toMediaType()
                            every { contentLength() } returns 1000L
                        }
                )

                brokerApi = mockk {
                    coEvery { getProperties() } returns restaurantsResponseError
                }
                assertNotEquals(propertiessResponseSuccess, brokerApi.getProperties())
                assertEquals(restaurantsResponseError, brokerApi.getProperties())
            }

    @Test
    fun `when api returns 500 should return empty list`() =
            runBlocking {
                val restaurantsResponseError = Response.error<PropertyListResponse>(
                        500,
                        mockk<ResponseBody>() {
                            every { contentType() } returns "application/json; charset=UTF-8".toMediaType()
                            every { contentLength() } returns 1000L
                        }
                )
                brokerApi = mockk {
                    coEvery { getProperties() } returns restaurantsResponseError
                }

                assertNotEquals(propertiessResponseSuccess, brokerApi.getProperties())
                assertEquals(restaurantsResponseError, brokerApi.getProperties())
            }

    @Test
    fun `when api returns 200 should return a list`() =
            runBlocking {
                val restaurantsResponseError = Response.success(propertiessResponseSuccess)

                brokerApi = mockk {
                    coEvery { getProperties() } returns propertiessResponseSuccess
                }
                assertEquals(propertiessResponseSuccess, brokerApi.getProperties())
                assertNotEquals(restaurantsResponseError, brokerApi.getProperties())
            }

    @Test
    @ExperimentalCoroutinesApi
    fun `when api returns with success repository should return a list`() =
            runBlockingTest {
                propertyRepository = mockk {
                    every { getProperties() } returns flow { emit(PropertyMock.properties) }
                }
                propertyRepository.getProperties().collect {
                    assertEquals(PropertyMock.properties, it)
                    assertNotEquals(emptyList<Property>(), it)
                }
            }

    @Test
    @ExperimentalCoroutinesApi
    fun `when api returns with success repository should return a empty list`() =
            runBlockingTest {
                propertyRepository = mockk {
                    every { getProperties() } returns flow { emit(emptyList<Property>()) }
                }
                propertyRepository.getProperties().collect {
                    assertNotEquals(PropertyMock.properties, it)
                    assertEquals(emptyList<Property>(), it)
                }
            }
}