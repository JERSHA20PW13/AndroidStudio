package com.example.finallab20pw13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}