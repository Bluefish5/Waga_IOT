package com.example.controlmaster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AA_RecyclerViewAdapterFile(private val devices:Array<String>) : RecyclerView.Adapter<AA_RecyclerViewAdapterFile.ViewHolder>()
{
    var onItemClick : ((String) ->Unit)? = null
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)

        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.filemanager_row,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int { return devices.size }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = devices[position]
        holder.textView.text = currentItem
        holder.textView.setOnClickListener{
            onItemClick?.invoke(currentItem)
        }
    }


}
