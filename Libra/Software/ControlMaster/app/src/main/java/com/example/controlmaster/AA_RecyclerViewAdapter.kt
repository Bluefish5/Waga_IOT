package com.example.controlmaster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AA_RecyclerViewAdapter(private val devices:Array<Device>) : RecyclerView.Adapter<AA_RecyclerViewAdapter.ViewHolder>()
{
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int { return devices.size }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = devices[position]
        holder.textView.text = currentItem.name
    }
}
