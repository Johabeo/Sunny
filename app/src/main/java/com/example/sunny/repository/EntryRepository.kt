package com.example.sunny.repository

import androidx.lifecycle.LiveData
import com.example.sunny.data.Entry
import com.example.sunny.data.EntryDao

class EntryRepository(private val entryDao : EntryDao) {

    val readAllData: LiveData<List<Entry>> = entryDao.readAllData()

    suspend fun addEntry(entry : Entry){
        entryDao.addEntry(entry)
    }

    suspend fun updateEntry(entry: Entry){
        entryDao.updateEntry(entry)
    }

    suspend fun deleteEntry(entry: Entry){
        entryDao.deleteEntry(entry)
    }

    suspend fun deleteAllEntries(){
        entryDao.deleteAllEntries()
    }
}