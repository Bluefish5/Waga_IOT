package com.example.noteapp

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.addButton)
            .setOnClickListener {
                addNewFile()
            }
        findViewById<Button>(R.id.deleteButton)
            .setOnClickListener {
                deleteLastFile(this)
            }
        findViewById<Button>(R.id.refreshButton)
            .setOnClickListener {
                refreshList(this)
            }

    }
    private fun createFile(context: Context, fileName: String, fileContent: String) {
        try {
            val fileOutputStream: FileOutputStream = context.openFileOutput(fileName, MODE_PRIVATE)
            fileOutputStream.write(fileContent.toByteArray())
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    private fun refreshList(context: Context){
        val internalStorageDir = context.filesDir
        val files = internalStorageDir.listFiles()
        val fileNames = mutableListOf<String>()
        files?.forEach { file ->
            fileNames.add(file.name)
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fileNames)
        findViewById<ListView>(R.id.listView).adapter = adapter

    }
    private fun addNewFile(){
        val name = findViewById<EditText>(R.id.editTextName).text.toString()
        val content = findViewById<EditText>(R.id.editTextContent).text.toString()
        createFile(this,name,content)
    }

    private fun deleteLastFile(context: Context): Boolean {
        val internalStorageDir = context.filesDir
        val files = internalStorageDir.listFiles()
        if (files.isNullOrEmpty()) {
            return false
        }
        val lastFile = files.maxByOrNull { it.lastModified() }
        val fileContent = readFileContent(this, lastFile?.name.toString())
        Toast.makeText(this, fileContent, Toast.LENGTH_SHORT).show()
        return lastFile?.delete() ?: false
    }
    private fun readFileContent(context: Context, fileName: String): String {
        return try {
            val fileInputStream = context.openFileInput(fileName)
            fileInputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error reading file: ${e.message}"
        }
    }
}
