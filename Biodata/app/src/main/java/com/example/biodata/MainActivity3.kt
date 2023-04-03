package com.example.biodata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val display = findViewById<TextView>(R.id.display)

        val intent = intent.extras

        display.text = "Hi "+intent?.getString("FName").toString()+" You are successfully registered!"

    }
}