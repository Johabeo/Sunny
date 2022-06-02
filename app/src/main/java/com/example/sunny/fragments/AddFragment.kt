package com.example.sunny.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sunny.R
import com.example.sunny.data.Entry
import com.example.sunny.EntryViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mEntryViewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mEntryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)

        view.add.setOnClickListener {
            insertDataToDatabase()
        }

        return view
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
            mEntryViewModel.addEntry(entry)
            Toast.makeText(requireContext(), "Journal Entry Added", Toast.LENGTH_LONG).show()
            // auto navigate user back to the list fragment
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out required fields",
                Toast.LENGTH_LONG).show()
        }
    }

    // this is the field level validation, will return true if all specified fields are inputted
    // correctly, i can specifically choose which fields are necessary and their type
    private fun inputCheck(date : String, title : String, mood: Editable): Boolean{
        return !(TextUtils.isEmpty(date) && TextUtils.isEmpty(title) && mood.isEmpty())
    }
}

