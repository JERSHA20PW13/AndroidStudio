package com.example.feedback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val button = findViewById<Button>(R.id.button)
        val rateMsg = findViewById<TextView>(R.id.rateMsg)
        val input = findViewById<EditText>(R.id.input)
        val feedback = findViewById<TextView>(R.id.feedback)

        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, _ ->
            if(rating == 5.0F)
                rateMsg.text = "Awesome. I love it"
            else if(rating == 4.0F)
                rateMsg.text = "Good. Enjoyed it"
            else if(rating == 3.0F)
                rateMsg.text = "Satisfied."
            else if(rating == 2.0F)
                rateMsg.text = "Not good. Need improvement"
            else if(rating == 1.0F)
                rateMsg.text = "Disappointed. Very poor"
            else
                rateMsg.text = "Rate us here"
        }

        button.setOnClickListener() {
            if(ratingBar.rating == 0F) {
                feedback.text = ""
                Toast.makeText(applicationContext, "Give a rating first!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            feedback.text = "Feedback: "+input.text.toString()
            input.setText("")
        }
    }
}
