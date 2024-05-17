package com.example.controlmaster

import android.content.Context
import org.simpleframework.xml.core.Persister
import java.io.File
import java.io.FileOutputStream

class AppDataManager {
    val listOfDevices = listOf("waga","starter")


    fun getDevicesList(): List<String> {
        return listOfDevices
    }
    fun getDevices(): Device {
        val serializer = Persister()
        val source = File("app/res/values/devices.xml")
        val device: Device = serializer.read(Device::class.java, source)
        return device
    }

    fun writeToFile(context: Context, fileName: String, fileContent: String) {
        val file = File(context.filesDir, fileName)
        FileOutputStream(file).use {
            it.write(fileContent.toByteArray())
        }
    }

    fun readFromFile(context: Context, fileName: String): String {
        val file = File(context.filesDir, fileName)
        return if (file.exists()) {
            file.readText()
        } else {
            "File not found"
        }
    }

    fun createFolderInInternalStorage(context: Context, folderName: String): Boolean {
        val folder = File(context.filesDir, folderName)
        return if (!folder.exists()) {
            folder.mkdirs()
        } else {
            true
        }
    }

    fun writeToFileInFolder(context: Context, folderName: String, fileName: String, fileContent: String) {
        val folder = File(context.filesDir, folderName)
        if (folder.exists() || folder.mkdirs()) {
            val file = File(folder, fileName)
            FileOutputStream(file).use {
                it.write(fileContent.toByteArray())
            }
        }
    }

    fun readFromFileInFolder(context: Context, folderName: String, fileName: String): String {
        val folder = File(context.filesDir, folderName)
        val file = File(folder, fileName)
        return if (file.exists()) {
            file.readText()
        } else {
            "File not found"
        }
    }

    // Usage Example
    fun exampleUsage(context: Context) {
        val folderName = "myFolder"
        val fileName = "myFile.txt"
        val fileContent = "Hello, World!"

        if (createFolderInInternalStorage(context, folderName)) {
            writeToFileInFolder(context, folderName, fileName, fileContent)
            val content = readFromFileInFolder(context, folderName, fileName)
            println(content) // Output: Hello, World!
        }
    }

    fun listFilesInDirectory(directory: File, indent: String = ""): String {
        val result = StringBuilder()
        if (directory.exists() && directory.isDirectory) {
            val files = directory.listFiles()
            if (files != null) {
                for (file in files) {
                    result.append("$indent${file.name}\n")
                    if (file.isDirectory) {
                        result.append(listFilesInDirectory(file, "$indent  "))
                    }
                }
            }
        }
        return result.toString()
    }
    fun listInternalStorageFiles(context: Context): String {
        val internalStorageRoot = context.filesDir
        return listFilesInDirectory(internalStorageRoot)
    }

}