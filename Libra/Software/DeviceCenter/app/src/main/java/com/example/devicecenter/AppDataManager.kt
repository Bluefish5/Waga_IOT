//package com.example.controlmaster
//
//import android.content.Context
//import java.io.File
//import java.io.FileOutputStream
//import java.io.IOException
//
//class AppDataManager {
//    val listOfDevices = listOf("waga","starter")
//
//
//    fun getDevicesList(): List<String> {
//        return listOfDevices
//    }
//    fun csvConverter(): MutableList<String>{
//        var filePath = "example.txt"
//        val file = File(filePath)
//        var Sum = mutableListOf<String>()
//
//        file.forEachLine { line ->
//            Sum.add(line)
//        }
//        return Sum
//    }
//
//    fun writeToFile(context: Context, fileName: String, fileContent: String) {
//        val file = File(context.filesDir, fileName)
//        FileOutputStream(file).use {
//            it.write(fileContent.toByteArray())
//        }
//    }
//
//    fun createFile(context: Context, fileName: String, fileContent: String) {
//        try {
//            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
//                output.write(fileContent.toByteArray())
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }
//    fun doesFileExist(context: Context, fileName: String): Boolean {
//        val file: File = context.getFileStreamPath(fileName)
//        return file.exists()
//    }
//
//    fun readFile(context: Context, fileName: String): String? {
//        return try {
//            context.openFileInput(fileName).bufferedReader().use { it.readText() }
//        } catch (e: IOException) {
//            e.printStackTrace()
//            null
//        }
//    }
//
//    fun listFiles(context: Context): Array<String> {
//        return context.fileList()
//    }
//
//    fun listFilesContaining(context: Context, substring: String): Array<String> {
//        return context.fileList().filter { it.contains(substring) }.toTypedArray()
//    }
//
//    fun readFromFile(context: Context, fileName: String): String {
//        val file = File(context.filesDir, fileName)
//        return if (file.exists()) {
//            file.readText()
//        } else {
//            "File not found"
//        }
//    }
//
//    fun createFolderInInternalStorage(context: Context, folderName: String): Boolean {
//        val folder = File(context.filesDir, folderName)
//        return if (!folder.exists()) {
//            folder.mkdirs()
//        } else {
//            true
//        }
//    }
//
//    fun writeToFileInFolder(context: Context, folderName: String, fileName: String, fileContent: String) {
//        val folder = File(context.filesDir, folderName)
//        if (folder.exists() || folder.mkdirs()) {
//            val file = File(folder, fileName)
//            FileOutputStream(file).use {
//                it.write(fileContent.toByteArray())
//            }
//        }
//    }
//
//    fun readFromFileInFolder(context: Context, folderName: String, fileName: String): String {
//        val folder = File(context.filesDir, folderName)
//        val file = File(folder, fileName)
//        return if (file.exists()) {
//            file.readText()
//        } else {
//            "File not found"
//        }
//    }
//
//    // Usage Example
//    fun exampleUsage(context: Context) {
//        val folderName = "myFolder"
//        val fileName = "myFile.txt"
//        val fileContent = "Hello, World!"
//
//        if (createFolderInInternalStorage(context, folderName)) {
//            writeToFileInFolder(context, folderName, fileName, fileContent)
//            val content = readFromFileInFolder(context, folderName, fileName)
//            println(content) // Output: Hello, World!
//        }
//    }
//
//    fun listFilesInDirectory(directory: File, indent: String = ""): String {
//        val result = StringBuilder()
//        if (directory.exists() && directory.isDirectory) {
//            val files = directory.listFiles()
//            if (files != null) {
//                for (file in files) {
//                    result.append("$indent${file.name}\n")
//                    if (file.isDirectory) {
//                        result.append(listFilesInDirectory(file, "$indent  "))
//                    }
//                }
//            }
//        }
//        return result.toString()
//    }
//    fun listInternalStorageFiles(context: Context): String {
//        val internalStorageRoot = context.filesDir
//        return listFilesInDirectory(internalStorageRoot)
//    }
//
//}