package com.joaoibarra.broker.data.db.property

import com.joaoibarra.broker.data.remote.domain.property.PropertyListResponse
import io.mockk.every
import io.mockk.mockk

object PropertyMock {
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

    val property = mockk<Property> {
        every { type } returns PropertyType.Property
        every { id } returns 28
        every { streetAddress } returns "Rua General Arthur Sother, 430"
        every { municipality } returns "Campo Grande"
        every { area } returns "Jardim Samambaia"
        every { askingPrice } returns "2 966 000 kr"
        every { monthlyFee } returns "1 498 kr/mån"
        every { daysOnHemnet } returns 15
        every { livingArea } returns 150f
        every { numberOfRooms } returns 4
        every { image } returns "https://pt.wikipedia.org/wiki/Morada_dos_Ba%C3%ADs#/media/Ficheiro:Morada_dos_Ba%C3%ADs.JPG"
        every { averagePrice } returns null
        every { rating } returns null
    }

    val areaProperty = mockk<Property> {
        every { type } returns PropertyType.Area
        every { id } returns 29
        every { streetAddress } returns null
        every { municipality } returns null
        every { area } returns "Rio de Janeiro"
        every { askingPrice } returns null
        every { monthlyFee } returns null
        every { daysOnHemnet } returns null
        every { livingArea } returns null
        every { numberOfRooms } returns null
        every { image } returns "https://pt.wikipedia.org/wiki/Museu_de_Arte_de_S%C3%A3o_Paulo#/media/Ficheiro:MASP_Brazil.jpg"
        every { averagePrice } returns "1 966 000 kr"
        every { rating } returns "4.2/5"
    }

    val properties = listOf(
        highlightedProperty,
        property,
        areaProperty
    )

    val propertyListResponse = mockk<PropertyListResponse>()
}