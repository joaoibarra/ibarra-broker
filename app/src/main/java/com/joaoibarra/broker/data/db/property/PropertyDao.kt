package com.joaoibarra.broker.data.db.property

import androidx.room.*

@Dao
interface PropertyDao {
    @Query("SELECT * FROM property")
    fun getAll() : List<Property>

    @Query("SELECT * FROM property WHERE id LIKE :propertyId")
    fun findById(propertyId: Int): Property?

    @Query("SELECT COUNT() FROM property")
    fun getCount() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(property: Property)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(property: List<Property>)

    @Update
    fun update(property: Property)
}