package com.sales.g10.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = arrayOf(LoginDetails::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
public abstract class GtenDatabase : RoomDatabase() {

    abstract fun loginDetailsDao(): LoginDetailsDao

    companion object {
        @Volatile
        private var INSTANCE: GtenDatabase? = null

        fun getDatabase(context: Context): GtenDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GtenDatabase::class.java,
                    "gten_database"
                ).setJournalMode(JournalMode.TRUNCATE)
                    //.addMigrations()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}