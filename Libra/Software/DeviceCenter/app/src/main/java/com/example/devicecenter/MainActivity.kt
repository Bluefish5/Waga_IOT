package com.example.devicecenter

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.controlmaster.AA_RecyclerViewAdapter
import com.example.controlmaster.Device
import com.example.controlmaster.FileManagerActivity
import com.example.devicecenter.ui.theme.DeviceCenterTheme

class MainActivity : ComponentActivity() {

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
    }
}

