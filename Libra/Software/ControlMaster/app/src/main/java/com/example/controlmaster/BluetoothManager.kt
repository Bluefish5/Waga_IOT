//package com.example.controlmaster
//
//import android.Manifest
//import android.app.Activity
//import android.bluetooth.BluetoothAdapter
//import android.bluetooth.BluetoothDevice
//import android.bluetooth.BluetoothSocket
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.content.IntentFilter
//import android.content.pm.PackageManager
//import android.widget.Toast
//import androidx.core.app.ActivityCompat
//import androidx.core.app.ActivityCompat.startActivityForResult
//import java.io.IOException
//import java.util.UUID
//
//class BluetoothManager {
//
//    val REQUEST_ENABLE_BT = 1
//
//    fun enableBluetooth(activity: Activity) {
//        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
//        if (bluetoothAdapter == null) {
//            Toast.makeText(activity, "Device doesn't support Bluetooth", Toast.LENGTH_SHORT).show()
//        } else {
//            if (!bluetoothAdapter.isEnabled) {
//                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//                if (ActivityCompat.checkSelfPermission(
//                        this,
//                        Manifest.permission.BLUETOOTH_CONNECT
//                    ) != PackageManager.PERMISSION_GRANTED
//                ) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return
//                }
//                activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
//            } else {
//                Toast.makeText(activity, "Bluetooth is already enabled", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//    fun discoverDevices(bluetoothAdapter: BluetoothAdapter) {
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.BLUETOOTH_SCAN
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//        if (bluetoothAdapter.isDiscovering) {
//            bluetoothAdapter.cancelDiscovery()
//        }
//        bluetoothAdapter.startDiscovery()
//    }
//
//
//    val discoveryBroadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            val action = intent?.action
//            if (BluetoothDevice.ACTION_FOUND == action) {
//                val device: BluetoothDevice? = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
//                device?.let {
//                    // Handle the discovered device
//                    // e.g., add it to a list or display it
//                    if (ActivityCompat.checkSelfPermission(
//                            this,
//                            Manifest.permission.BLUETOOTH_CONNECT
//                        ) != PackageManager.PERMISSION_GRANTED
//                    ) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return
//                    }
//                    println("Device found: ${it.name} - ${it.address}")
//                }
//            }
//        }
//    }
//
//    fun registerDiscoveryReceiver(context: Context) {
//        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
//        context.registerReceiver(discoveryBroadcastReceiver, filter)
//    }
//
//
//    val MY_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB") // Standard SerialPortService ID
//
//    fun connectToDevice(device: BluetoothDevice) {
//        val socket: Unit = if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.BLUETOOTH_CONNECT
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//            device.createRfcommSocketToServiceRecord(MY_UUID)
//        try {
//            socket?.connect()
//            // Connection successful, manage the connection
//            if (socket != null) {
//                manageConnectedSocket(socket)
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//            // Unable to connect, close the socket
//            try {
//                socket?.close()
//            } catch (closeException: IOException) {
//                closeException.printStackTrace()
//            }
//        }
//    }
//
//
//
//    fun manageConnectedSocket(socket: BluetoothSocket) {
//        val inputStream: InputStream = socket.inputStream
//        val outputStream: OutputStream = socket.outputStream
//
//        // Example: Sending data
//        val message = "Hello, Bluetooth!"
//        outputStream.write(message.toByteArray())
//
//        // Example: Receiving data
//        val buffer = ByteArray(1024)
//        val bytes = inputStream.read(buffer)
//        val receivedMessage = String(buffer, 0, bytes)
//        println("Received: $receivedMessage")
//    }
//
//
//
//
//
//}