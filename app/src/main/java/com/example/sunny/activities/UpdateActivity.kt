package com.example.sunny.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.example.sunny.R
import com.example.sunny.data.Entry
import com.example.sunny.data.EntryViewModel
import com.example.sunny.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.activity_update.view.*
import java.nio.file.Files.delete
import kotlin.reflect.KProperty

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: EntryViewModel
    lateinit var updatedEntry: Entry

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_update)
        mainViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)

        binding.recyclerView.update.setOnClickListener {
        updateItem()
        }
    }

    private fun updateItem(){
        val date = updateDate.text.toString()
        val title = updateTitle.text.toString()
        val entry = updateEntry.text.toString()
        val mood = Integer.parseInt(updateMood.text.toString())
        val gratitude = updateGratitude.text.toString()
        val goal = updateGoals.text.toString()

        if(inputCheck(date, title, updateMood.text)){
            // create entry object
            updatedEntry = Entry(0, date, title, entry, mood, gratitude, goal)
            // update current user
            mainViewModel.updateEntry(updatedEntry)
            Toast.makeText(this, "Journal Entry Updated", Toast.LENGTH_LONG).show()
            // going back to list fragment
            val myintent = Intent(this, MainActivity::class.java)
            startActivity(myintent)
        } else {
            Toast.makeText(this, "Please fill out required fields",
                Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(date : String, title : String, mood: Editable): Boolean{
        return !(TextUtils.isEmpty(date) && TextUtils.isEmpty(title) && mood.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteEntry()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteEntry() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") {_, _ ->
            deleteEntry()
            Toast.makeText(this, "Entry #${updatedEntry.id} Deleted",
                Toast.LENGTH_SHORT).show()
            val myintent = Intent(this, MainActivity::class.java)
            startActivity(myintent)
        }
        builder.setPositiveButton("No") {_, _ ->
            builder.setTitle("Delete Entry #${updatedEntry.id}?")
            builder.setMessage("Are you sure you want to delete Entry #${updatedEntry.id}?")
            builder.create().show()
        }
    }
}





