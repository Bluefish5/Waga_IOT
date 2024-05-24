package com.example.controlmaster

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CsvFileActivity : AppCompatActivity() {

    val appData = AppDataManager()

    companion object {
        const val FILE_NAME = "example.txt"
        const val FILE_CONTENT = "Hello, World!"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_csv_file)

        val buttonBack: Button = findViewById(R.id.buttonBackCsv)

        buttonBack.setOnClickListener{
            finish()
        }

        val fileExists = appData.doesFileExist(this, FileManagerActivity.FILE_NAME)
        if (fileExists) {
            val fileContent = appData.csvConverter()
            Toast.makeText(this, fileContent.toString(), Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this, "File does not exist!", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}

