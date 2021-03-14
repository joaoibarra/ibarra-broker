package com.joaoibarra.broker.data.db.property

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Property(
    @PrimaryKey(autoGenerate = false) val id: Int?,
    @ColumnInfo(name = "type") val type: PropertyType?,
    @ColumnInfo(name = "askingPrice") val askingPrice: String?,
    @ColumnInfo(name = "monthlyFee") val monthlyFee: String?,
    @ColumnInfo(name = "municipality") val municipality: String?,
    @ColumnInfo(name = "area") val area: String?,
    @ColumnInfo(name = "daysOnHemnet") val daysOnHemnet: Int?,
    @ColumnInfo(name = "livingArea") val livingArea: Float?,
    @ColumnInfo(name = "numberOfRooms") val numberOfRooms: Int?,
    @ColumnInfo(name = "streetAddress") val streetAddress: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "rating") val rating: String?,
    @ColumnInfo(name = "averagePrice") val averagePrice: String?
) {

    override fun equals(other: Any?): Boolean {
        return when(other) {
            is Property -> {
                id == other.id && type == other.type
            }
            else -> false
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}