package com.example.controlmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FileManagerActivity : ComponentActivity() {
    val appData = AppDataManager()

    companion object {
        const val FILE_NAME = "example.txt"
        const val FILE_CONTENT = "Hello, World!"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_manager)

        val buttonBack: Button = findViewById(R.id.buttonBack)


        val filteredFiles = appData.listFilesContaining(this, "example")

        val recyclerView: RecyclerView = findViewById(R.id.myRecyclerView)
        var deviceAdapter = AA_RecyclerViewAdapterFile(filteredFiles)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = deviceAdapter

        buttonBack.setOnClickListener{
            finish()
        }


        deviceAdapter.onItemClick = {
            val intent = Intent(this,CsvFileActivity::class.java)
            intent.putExtra("device",it)
            startActivity(intent)
        }
    }



    }


