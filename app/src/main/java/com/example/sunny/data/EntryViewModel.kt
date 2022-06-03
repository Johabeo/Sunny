package com.example.sunny.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.sunny.data.Entry
import com.example.sunny.data.EntryDatabase
import com.example.sunny.data.EntryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntryViewModel(application: Application): AndroidViewModel(application) {

    val allEntries: LiveData<List<Entry>>
    private val repository: EntryRepository

    /* Database is initialized */
    init {
       // val entryDao = EntryDatabase.getDatabase(application).entryDao()
        repository = EntryRepository(application)
        allEntries = repository.getAllEntries()!!
    }

    /* All members are excluding read are abstracted through the viewmodel */
    fun addEntry(entry: Entry){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEntry(entry)
        }
    }

    fun updateEntry(entry: Entry){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateEntry(entry)
        }
    }

    fun searchJournal(searchQuery: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.searchJournal(searchQuery)
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