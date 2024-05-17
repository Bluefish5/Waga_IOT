package com.example.controlmaster

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.Serializer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {
    val appData = AppDataManager()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var devices: Array<Device> = arrayOf(Device(1,"Waga",""),
            Device(2,"Starter",""))

        val recyclerView: RecyclerView = findViewById(R.id.myRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = AA_RecyclerViewAdapter(devices)



    }
}