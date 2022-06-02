package com.example.sunny.data

import androidx.lifecycle.LiveData
import com.example.sunny.data.Entry
import com.example.sunny.data.EntryDao
import com.example.sunny.data.EntryDatabase

class EntryRepository(private val entryDao: EntryDao) {

    val readAllEntries: LiveData<List<Entry>> = entryDao.readAllEntries()

//    fun getAllEntries(): LiveData<List<Entry>> {
//        return (db?.selectEntries() ?: listOf<Entry>()) as LiveData<List<Entry>>
//    }

    suspend fun insertEntry(entry : Entry) {
        entryDao.insertEntry(entry)
    }

    suspend fun updateEntry(entry : Entry) {
        entryDao.updateEntry(entry)
    }

    suspend fun deleteEntry(entry : Entry){
        entryDao.deleteEntry(entry)
    }

    suspend fun deleteAllEntries(){
        entryDao.deleteAllEntries()
    }


}