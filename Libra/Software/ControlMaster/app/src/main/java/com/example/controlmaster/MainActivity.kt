package com.example.controlmaster

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.core.Serializer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {


    private lateinit var deviceAdapter: AA_RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var devices: Array<Device> = arrayOf(
            Device(1, "Waga", ""),
            Device(2, "Starter", "")
        )





        val recyclerView: RecyclerView = findViewById(R.id.myRecyclerView)

        deviceAdapter = AA_RecyclerViewAdapter(devices)


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = deviceAdapter

        deviceAdapter.onItemClick = {
            val intent = Intent(this,FileManagerActivity::class.java)
            intent.putExtra("device",it)
            startActivity(intent)
        }

    }

}