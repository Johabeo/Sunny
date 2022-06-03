package com.example.sunny.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {

    /*
    Create
    uses conflict strategy to for duplicate id's
    */
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insertEntry(entry: Entry)

    /*
    Read
    the database query is pre-sorted by date, like a real journal
    */
    @Query("SELECT * FROM journal ORDER BY date ASC")
    fun getAllEntries(): LiveData<List<Entry>>

    /*
    The search function member, returns data when the search text matches the date or title
     */
    @Query("SELECT * FROM journal WHERE date LIKE :searchQuery OR title LIKE :searchQuery")
    fun searchJournal(searchQuery: String): LiveData<List<Entry>>

    /* Update */
    @Update
    fun updateEntry(entry: Entry)

    /* Delete */
    @Delete
    fun deleteEntry(entry: Entry)

    /* Delete all */
    @Query("DELETE FROM journal")
    fun deleteAllEntries()

}