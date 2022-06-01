package com.example.sunny.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EntryDao {

    //uses a strategy based on name conflict to avoid duplicate entries
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEntry(entry: Entry)

    @Update
    suspend fun updateEntry(entry: Entry)

    @Delete
    suspend fun deleteEntry(entry: Entry)

    //Read
    @Query("select * from journal")
    fun selectStudents(): List<Entry>

    @Query("delete from journal")
    suspend fun deleteAllEntries()

    //auto-sorts the return value by date
    @Query("select * from journal order by date asc")
        fun readAllData(): LiveData<List<Entry>>

}