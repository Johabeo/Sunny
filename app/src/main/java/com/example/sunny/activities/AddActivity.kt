package com.example.sunny.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.example.sunny.R
import com.example.sunny.data.Entry
import com.example.sunny.data.EntryViewModel
import com.example.sunny.databinding.ActivityAddBinding
import com.example.sunny.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_add.view.*

private lateinit var mainViewModel: EntryViewModel
private lateinit var binding: ActivityMainBinding

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)

        binding.recyclerView.add.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase(){
        val date = editDate.text.toString()
        val title = editTitle.text.toString()
        val entry = editEntry.text.toString()
        val mood = editMood.text
        val gratitude = editGratitude.text.toString()
        val goals = editGoals.text.toString()

        // this is the logic for the for the input check function below
        // where i can add toasts or texts asking to add the required fields/forms
        if(inputCheck(date, title, editMood.text)){
            //create Entry object
            val entry = Entry(0, date, title, entry, Integer.parseInt(mood.toString()),
                gratitude, goals)
            // add user to database view Viewmodel
            mainViewModel.addEntry(entry)
            Toast.makeText(this, "Journal Entry Added", Toast.LENGTH_LONG).show()
            // auto navigate user back to the list fragment
            val myintent = Intent(this, MainActivity::class.java)
            startActivity(myintent)
        }else{
            Toast.makeText(this, "Please fill out required fields",
                Toast.LENGTH_LONG).show()
        }
    }

    // this is the field level validation, will return true if all specified fields are inputted
    // correctly, i can specifically choose which fields are necessary and their type
    private fun inputCheck(date : String, title : String, mood: Editable): Boolean{
        return !(TextUtils.isEmpty(date) && TextUtils.isEmpty(title) && mood.isEmpty())
    }
}