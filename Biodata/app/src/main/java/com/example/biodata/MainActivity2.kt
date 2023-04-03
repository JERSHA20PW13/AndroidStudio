package com.example.biodata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val display = findViewById<TextView>(R.id.display)
        val intent = intent.extras

        display.text = intent?.getString("Details").toString()

        val edit = findViewById<Button>(R.id.button2)
        val confirm = findViewById<Button>(R.id.button3)

        edit.setOnClickListener {
            finish()
        }

        confirm.setOnClickListener {

            val intent1 = Intent(applicationContext, MainActivity3::class.java)

            intent1.putExtra("FName", intent?.getString("FName").toString())

            startActivity(intent1)
        }
    }
}