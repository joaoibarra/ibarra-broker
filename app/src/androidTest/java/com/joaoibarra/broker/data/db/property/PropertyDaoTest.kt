package com.joaoibarra.broker.data.db.property

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.Matchers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class PropertyDaoTest: KoinTest {
    private val propertyDao by inject<PropertyDao>()

    @Test
    @Throws(Exception::class)
    fun shouldFindPropertyThatExist() {
        val highlightedProperty = mockk<Property> {
            every { type } returns PropertyType.HighlightedProperty
            every { id } returns 27
            every { streetAddress } returns "Rua Jose Maria Lisboa, 730"
            every { municipality } returns "Sao Paulo"
            every { area } returns "Jardim Paulista"
            every { askingPrice } returns "6 966 000 kr"
            every { monthlyFee } returns "3 498 kr/mån"
            every { daysOnHemnet } returns 8
            every { livingArea } returns 60f
            every { numberOfRooms } returns 3
            every { image } returns "https://pt.wikipedia.org/wiki/Museu_de_Arte_de_S%C3%A3o_Paulo#/media/Ficheiro:MASP_Brazil.jpg"
            every { averagePrice } returns null
            every { rating } returns null
        }
        propertyDao.insertOrUpdate(highlightedProperty)
        val restaurantById = propertyDao.findById(27)
        assertEquals(restaurantById, highlightedProperty)
    }

    @Test
    @Throws(Exception::class)
    fun shouldNotFindPropertyThatNotExist() {
        propertyDao.insertOrUpdate(PropertyDaoMock.highlightedProperty)
        val restaurantById = propertyDao.findById(28)
        assertNotEquals(restaurantById, Matchers.equalTo(PropertyDaoMock.highlightedProperty))
        assertEquals(null, restaurantById)
    }

    @Test
    @Throws(Exception::class)
    fun whenInsertAListOfPropertiesShouldReturnRightCount() {
        propertyDao.insertList(PropertyDaoMock.properties)
        val count = propertyDao.getCount()
        assertEquals(3, count)
    }

    @Test
    @Throws(Exception::class)
    fun whenInsertAListOfPropertiesShouldReturnRightList() {
        propertyDao.insertList(PropertyDaoMock.properties)
        val propertieDbList = propertyDao.getAll()
        assertEquals(propertieDbList, PropertyDaoMock.properties)
    }
}