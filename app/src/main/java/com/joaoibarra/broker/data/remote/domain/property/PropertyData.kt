package com.joaoibarra.broker.data.remote.domain.property

import com.joaoibarra.broker.data.db.property.Property
import com.joaoibarra.broker.data.db.property.PropertyType

data class PropertyData (
    val id: Int?,
    val type: String?,
    val askingPrice: String?,
    val monthlyFee: String?,
    val municipality: String?,
    val area: String?,
    val daysOnHemnet: Int?,
    val livingArea: Float?,
    val numberOfRooms: Int?,
    val streetAddress: String?,
    val image: String?,
    val rating: String?,
    val averagePrice: String?
) {
    fun toDb() = Property(
        id = id,
        type = type?.let { PropertyType.valueOf(it) } ?: PropertyType.Property,
        askingPrice = askingPrice?.trim(),
        monthlyFee = monthlyFee?.trim(),
        municipality = municipality?.trim(),
        area = area?.trim(),
        daysOnHemnet = daysOnHemnet,
        livingArea = livingArea,
        numberOfRooms = numberOfRooms,
        streetAddress = streetAddress?.trim(),
        image = image?.trim(),
        rating = rating?.trim(),
        averagePrice = averagePrice?.trim()
    )
}