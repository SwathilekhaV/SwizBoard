package com.example.swizboard

import android.R
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket

class MainActivity : AppCompatActivity() {
    private var whiteboardView: WhiteboardView? = null
    private var socket: Socket? = null
    private var outputStream: DataOutputStream? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        whiteboardView = findViewById(R.id.whiteboardView)
        val connectButton = findViewById<Button>(R.id.connectButton)

        connectButton.setOnClickListener { connectToServer() }

        whiteboardView.setOnDrawListener { x, y -> sendCoordinatesToServer(x, y) }
    }

    private fun connectToServer() {
        Thread {
            try {
                socket = Socket("SERVER_IP", 12345) // replace SERVER_IP with actual IP
                outputStream = DataOutputStream(socket!!.getOutputStream())
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun sendCoordinatesToServer(x: Float, y: Float) {
        Thread {
            try {
                if (outputStream != null) {
                    outputStream!!.writeFloat(x)
                    outputStream!!.writeFloat(y)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
    }
}