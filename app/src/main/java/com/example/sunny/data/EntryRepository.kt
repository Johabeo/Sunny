package com.example.sunny.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sunny.data.Entry
import com.example.sunny.data.EntryDao
import com.example.sunny.data.EntryDatabase
import kotlinx.coroutines.flow.Flow

class EntryRepository(context: Context) {

    var db: EntryDao? = EntryDatabase.getDatabase(context)?.entryDao()

    fun getAllEntries(): LiveData<List<Entry>>? {
        return db?.getAllEntries()  //returns an empty list if null
    }

    suspend fun insertEntry(entry : Entry) {
        db?.insertEntry(entry)
    }

    suspend fun updateEntry(entry : Entry) {
        db?.updateEntry(entry)
    }

    fun searchJournal(searchQuery: String): LiveData<List<Entry>> {
        return searchJournal(searchQuery)
    }

    suspend fun deleteEntry(entry : Entry){
        db?.deleteEntry(entry)
    }

    suspend fun deleteAllEntries(){
        db?.deleteAllEntries()
    }
}