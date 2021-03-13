package com.joaoibarra.broker.data.remote.domain.property

import com.joaoibarra.broker.data.db.property.Property

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
    val image: String?
) {
    fun toDb() = Property(
        id = id,
        type = type,
        askingPrice = askingPrice,
        monthlyFee = monthlyFee,
        municipality = municipality,
        area = area,
        daysOnHemnet = daysOnHemnet,
        livingArea = livingArea,
        numberOfRooms = numberOfRooms,
        streetAddress = streetAddress,
        image = image
    )
}