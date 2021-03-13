package com.joaoibarra.broker.data.remote.domain.property

import com.google.gson.annotations.SerializedName
import com.joaoibarra.broker.data.db.property.Property

data class PropertyListResponse (
    @SerializedName("items") val items: List<PropertyData>?
){
    fun toList(): List<Property> {
        return items?.map { it.toDb() } ?: emptyList()
    }
}