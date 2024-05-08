package com.example.controlmaster

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult

class BluetoothManager {
    val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    if (bluetoothAdapter == null) {
        // Device doesn't support Bluetooth
    } else {
        if (!bluetoothAdapter.isEnabled) {
            // Bluetooth is not enabled, request user to turn it on
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        } else {
            // Bluetooth is enabled, proceed with your Bluetooth functionality
        }
    }

}