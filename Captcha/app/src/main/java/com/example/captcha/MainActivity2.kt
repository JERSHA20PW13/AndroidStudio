package com.example.captcha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val details = intent.getStringExtra("mobNum")
        findViewById<TextView>(R.id.textView).text = "Welcome "+details

    }
}