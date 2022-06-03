package com.example.sunny.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunny.R
import com.example.sunny.activities.list.EntryAdapter
import com.example.sunny.data.Entry
import com.example.sunny.data.EntryRepository
import com.example.sunny.data.EntryViewModel
import com.example.sunny.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: EntryViewModel

    lateinit var entryList: List<Entry>

    val repo: EntryRepository by lazy {
        EntryRepository(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // var entryList = repo.getAllEntries()
        var adapter = EntryAdapter({ position -> onCardClick(position) }, entryList)
        binding.recyclerView.adapter = adapter
        mainViewModel.allEntries.observe(this) { adapter.setData(it) }

        binding.recyclerView.floatingActionButton.setOnClickListener {
            val myIntent = Intent(this, AddActivity::class.java)
            startActivity(myIntent)
        }


    }

    fun onCardClick(position: Int) {
        println("position:::$position")
        val myIntent = Intent(this, UpdateActivity::class.java)
        myIntent.putExtra("Id", entryList[position].id)
        myIntent.putExtra("Date", entryList[position].date)
        myIntent.putExtra("Title", entryList[position].title)
        startActivity(myIntent)
    }


    override fun OnCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?):Boolean {
        if(query!= null){
            searchDatabase(query)
        }
        return true
    }

    private fun searchDatabase(query:String) {
        val searchQuery = "%$query%"
         entryList.searchJournal(searchQuery).observe(this { list ->
            list.let {
                EntryAdapter.setData(it)
            }
        })
    }

    /* delete button functionality */
     fun OnCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean{
        if(item.itemId == R.id.menu_delete){
            deleteAllEntries()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllEntries() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mainViewModel.deleteAllEntries()
            Toast.makeText(
                this, "Journal Deleted",
                Toast.LENGTH_SHORT).show()
        }
        builder.setPositiveButton("No") { _, _ ->
            builder.setTitle("Delete journal?")
            builder.setMessage("Are you sure you want to delete the journal?")
            builder.create().show()
        }
    }
}