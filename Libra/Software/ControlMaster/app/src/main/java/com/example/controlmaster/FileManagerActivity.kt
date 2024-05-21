package com.example.controlmaster

import android.os.Bundle
import android.widget.Toast
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
import com.example.controlmaster.ui.theme.ControlMasterTheme

class FileManagerActivity : ComponentActivity() {
    val appData = AppDataManager()

    companion object {
        const val FILE_NAME = "example.txt"
        const val FILE_CONTENT = "Hello, World!"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_manager)

        val files = appData.listFiles(this)

        val filteredFiles = appData.listFilesContaining(this, "example")
        Toast.makeText(this, "Files containing 'example': ${filteredFiles.size}", Toast.LENGTH_LONG).show()
    }


//        appData.createFile(this, FILE_NAME, FILE_CONTENT)
//
//        val fileExists = appData.doesFileExist(this, FileManagerActivity.FILE_NAME)
//        if (fileExists) {
//            Toast.makeText(this, "File exists!", Toast.LENGTH_SHORT).show()
//            // Read from the file
//            val fileContent = appData.readFile(this, FileManagerActivity.FILE_NAME)
//            fileContent?.let {
//                Toast.makeText(this, "File content: $it", Toast.LENGTH_LONG).show()
//            }
//        } else {
//            Toast.makeText(this, "File does not exist!", Toast.LENGTH_SHORT).show()
//        }
    }


