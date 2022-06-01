package com.example.sunny.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sunny.R
import com.example.sunny.data.Entry
import com.example.sunny.model.viewmodel.EntryViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private lateinit var mEntryViewModel: EntryViewModel

    private var args by navArgs<UpdateFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mEntryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)

        view.updateDate.setText((args.currentUser.date))
        view.updateTitle.setText((args.currentUser.title))
        view.updateEntry.setText((args.currentUser.entry))
        view.updateMood.setText((args.currentUser.mood.toString()))
        view.updateGratitude.setText((args.currentUser.gratitudeList))
        view.updateGoals.setText((args.currentUser.goalList))

        view.update.setOnClickListener{
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
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
            val updatedEntry = Entry(args.currentEntry.id, date, title, entry, mood, gratitude, goal)
            // update current user
            mEntryViewModel.updateEntry(updatedEntry)
            Toast.makeText(requireContext(), "Journal Entry Updated", Toast.LENGTH_LONG).show()
            // going back to list fragment
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out required fields",
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
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mEntryViewModel.deleteEntry(args.currentEntry)
            Toast.makeText(requireContext(), "Entry #${args.currentEntry.id} Deleted",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setPositiveButton("No") {_, _ ->
            builder.setTitle("Delete Entry #${args.currentEntry.id}?")
            builder.setMessage("Are you sure you want to delete Entry #${args.currentEntry.id}?")
            builder.create().show()
        }
    }
}
