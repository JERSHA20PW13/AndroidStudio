package com.example.ca120pw13

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity4 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val display = findViewById<TextView>(R.id.textView25)
        display.text = intent.getStringExtra("details")

        val info = findViewById<TextView>(R.id.textView27)
        info.text = intent.getStringExtra("info")

        val btn = findViewById<Button>(R.id.button4)

        btn.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity5::class.java)
            startActivity(intent)
        }
    }
}