package com.example.sunny.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.sunny.data.Entry
import com.example.sunny.data.EntryDao
import com.example.sunny.data.EntryDatabase
import com.example.sunny.repository.EntryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import content.Context

class EntryViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Entry>>
    private val repository: EntryRepository

    var db: EntryDao? = EntryDatabase.getInstance(context)?.EntryDao()!!

    init {
        val entryDao = EntryDatabase.getDatabase(application).entryDao()
        repository = EntryRepository(entryDao)
        readAllData = repository.readAllData
    }

    fun getAllEntries(): List<Entry> {
        return db?.selectStudents() ?: listOf<Students>()
    }

    fun addEntry(entry: Entry){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEntry(entry)
        }
    }

    fun updateEntry(entry: Entry){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateEntry(entry)
        }
    }

    fun deleteEntry(entry: Entry){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteEntry(entry)
        }
    }

    fun deleteAllEntries(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllEntries()
        }
    }
}