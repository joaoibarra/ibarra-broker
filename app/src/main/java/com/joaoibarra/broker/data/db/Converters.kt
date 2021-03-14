package com.joaoibarra.broker.data.db

import androidx.room.TypeConverter
import com.joaoibarra.broker.data.db.property.PropertyType

class Converters {
    @TypeConverter
    fun toPropertyType(value: Int) = enumValues<PropertyType>()[value]

    @TypeConverter
    fun fromPropertyType(value: PropertyType) = value.ordinal
}