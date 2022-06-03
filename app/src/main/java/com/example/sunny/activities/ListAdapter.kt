package com.example.sunny.activities.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sunny.R
import com.example.sunny.data.Entry

class EntryAdapter (private val onClick: (position: Int) -> kotlin.Unit,
                   private val entryList: List<Entry>): RecyclerView.Adapter<MyViewHolder>(){

    private var oldData = emptyList<Entry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //inflate a view and return it
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(view, onClick)
    }

    // returns the number of recyclerview objects we need; the number of records
    override fun getItemCount(): Int {
        return entryList.size
    }

    fun setData(newData: List<Entry>){
        oldData = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = entryList[position]
        holder.defaultid.text = currentItem.id.toString()
        holder.defaultdate.text = currentItem.date
        holder.defaulttitle.text = currentItem.title

        }
    }

class MyViewHolder(view: View, private val onClick: (position: Int) -> Unit):
        RecyclerView.ViewHolder(view), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        val defaultid : TextView = view.findViewById(R.id.defaultId)
        val defaultdate : TextView = view.findViewById(R.id.defaultDate)
        val defaulttitle : TextView = view.findViewById(R.id.defaultTitle)
        val defaultmood : TextView = view.findViewById(R.id.defaultId)
        val defaultgratitude : TextView = view.findViewById(R.id.defaultDate)
        val defaultgoal : TextView = view.findViewById(R.id.defaultTitle)
        override fun onClick(p0: View?) {
            val position = adapterPosition
            onClick(position)
    }
}