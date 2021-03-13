package com.joaoibarra.broker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joaoibarra.broker.data.db.property.Property
import com.joaoibarra.broker.data.db.property.PropertyDao

@Database(entities = [Property::class], version = AppDatabase.DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPropertyDao(): PropertyDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "broker.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,
                DB_NAME
            ).build()
    }
}