package com.example.ca2_20pw13

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        val button = findViewById<Button>(R.id.button)
        val registerBtn = findViewById<Button>(R.id.register)

        registerBtn.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity3::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}
