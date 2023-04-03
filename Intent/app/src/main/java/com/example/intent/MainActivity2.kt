package com.example.intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val receivedDetails = findViewById<TextView>(R.id.textView4)

        val intent = intent

        val msg = intent.getStringExtra("Message")
        receivedDetails.text = msg.toString()
    }
}