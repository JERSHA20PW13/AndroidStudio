package com.example.ca120pw13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {
    private lateinit var timerTextView: TextView
    private var timerCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        timerTextView = findViewById(R.id.textView8)
        startTimer()

        val submitBtn = findViewById<Button>(R.id.button3)

        submitBtn.setOnClickListener {
            val item1 = findViewById<EditText>(R.id.item1).text.toString()
            val item2 = findViewById<EditText>(R.id.item2).text.toString()
            val item3 = findViewById<EditText>(R.id.item3).text.toString()
            val item4 = findViewById<EditText>(R.id.item4).text.toString()

            var details = ""
            var total = 0.0

            if(item1 != "") {
                details += "\nNoodles - Rs. 100 - "+item1
                total += item1.toDouble()*100
            }
            if(item2 != "") {
                details += "\nMilkshake - Rs. 80 - "+item2
                total += item2.toDouble()*80
            }
            if(item3 != "") {
                details += "\nPizza - Rs. 300 - "+item3
                total += item3.toDouble()*300
            }
            if(item4 != "") {
                details += "\nBurger - Rs. 200 - "+item4
                total += item4.toDouble()*200
            }
            details += "\nTIMER: "+timerTextView.text
            details += "\n\nTOTAL: "+total

            val intentTo4 = Intent(applicationContext, MainActivity4::class.java)
            intentTo4.putExtra("details", details)
            intentTo4.putExtra("info", "Name: "+intent.getStringExtra("name")+"\nMobile Number: "+intent.getStringExtra("mobNum"))

            startActivity(intentTo4)

        }
    }

    private fun startTimer() {
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                timerCounter++
                val hours = timerCounter / 3600
                val minutes = (timerCounter % 3600) / 60
                val seconds = timerCounter % 60
                timerTextView.text = "%02d:%02d:%02d".format(hours, minutes, seconds)
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }
}