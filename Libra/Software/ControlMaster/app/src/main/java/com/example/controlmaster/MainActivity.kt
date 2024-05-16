package com.example.controlmaster

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val appData = AppDataManager()

    val recyclerView: RecyclerView = findViewById(R.id.myRecyclerView)
    val adapter = RecyclerViewAdapter(ArrayList())




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}