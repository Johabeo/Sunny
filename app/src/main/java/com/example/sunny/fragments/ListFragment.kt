package com.example.sunny.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sunny.R
import com.example.sunny.data.Entry
import com.example.sunny.fragments.list.ListAdapter
import com.example.sunny.EntryViewModel
import com.example.sunny.data.EntryRepository
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mEntryViewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mEntryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
        mEntryViewModel.readAllData.observe(viewLifecycleOwner, Observer { entry ->
        adapter.setData(entry)})

        // binding the floating action button from the list feature to the add feature
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)
        return view
    }

    //delete button functionality
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
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
        builder.setPositiveButton("Yes") {_, _ ->
            mEntryViewModel.deleteAllEntries()
            Toast.makeText(requireContext(), "Journal Deleted",
                Toast.LENGTH_SHORT).show()
        }
        builder.setPositiveButton("No") {_, _ ->
            builder.setTitle("Delete journal?")
            builder.setMessage("Are you sure you want to delete the journal?")
            builder.create().show()
        }
    }
}