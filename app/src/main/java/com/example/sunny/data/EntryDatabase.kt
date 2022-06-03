package com.example.sunny.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
Standard database class, if database does not exist a new one is created, but only one exists
for space complexity
 */
@Database(entities = [Entry::class], version = 1, exportSchema = false)
abstract class EntryDatabase : RoomDatabase() {

    abstract fun entryDao(): EntryDao

    companion object {

        @Volatile
        private var INSTANCE: EntryDatabase? = null
        fun getDatabase(context: Context): EntryDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EntryDatabase::class.java,
                    "journal"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}