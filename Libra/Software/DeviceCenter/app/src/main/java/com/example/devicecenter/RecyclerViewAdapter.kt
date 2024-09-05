//package com.example.controlmaster
//import android.content.ClipData
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//
//class RecyclerViewAdapter(private val itemList: MutableList<ClipData.Item>) :
//    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        // Define views here
//    }
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("asdas")
//    }
//
//    override fun getItemCount(): Int {
//        return itemList.size
//    }
//    fun addItem(item: ClipData.Item) {
//        itemList.add(item)
//        notifyItemInserted(itemList.size - 1)
//    }
//
//    // Method to remove an item from the RecyclerView
//    fun removeItem(position: Int) {
//        itemList.removeAt(position)
//        notifyItemRemoved(position)
//    }
//}
