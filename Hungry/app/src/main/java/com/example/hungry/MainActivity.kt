package com.example.hungry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)
        val img = findViewById<ImageView>(R.id.imageView)
        val text = findViewById<TextView>(R.id.textView)

        var count = 0

        btn.setOnClickListener {
            if(count % 2 == 0) {
                img.setImageResource(R.drawable.happy)
                text.setText("GOOD JOB !")
                btn.setText("DONE")
            }
            else {
                img.setImageResource(R.drawable.sad)
                text.setText("I AM HUNGRY !")
                btn.setText("EAT COOKIE")
            }
            count++
        }
    }
}
