package com.example.sunny.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EntryDao {

    // Create
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEntry(entry: Entry)

    // Read
    @Query("SELECT * FROM journal ORDER BY date ASC")
    fun readAllEntries(): LiveData<List<Entry>>

    // Update
    @Update
    suspend fun updateEntry(entry: Entry)

    // Delete
    @Delete
    suspend fun deleteEntry(entry: Entry)

    // Delete all
    @Query("DELETE FROM journal")
    suspend fun deleteAllEntries()
}