package com.example.captcha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val captcha = findViewById<TextView>(R.id.textView4)

        captcha.text = (Random.nextInt(900000)+100000).toString()

        val re = Regex("^[6-9]{1}[0-9]{9}$")

        val mobNum = findViewById<EditText>(R.id.editText1)
        val ipCaptcha = findViewById<EditText>(R.id.editText2)

        val btn = findViewById<Button>(R.id.button)

        btn.setBackgroundColor(getColor(R.color.orange))

        btn.setOnClickListener {

            if (!mobNum.text.toString().matches(re)) {
                Toast.makeText(applicationContext, "Invalid mobile number!", Toast.LENGTH_SHORT).show()
            } else {
                if (ipCaptcha.text.toString() == "") {
                    Toast.makeText(applicationContext, "Enter Captcha!", Toast.LENGTH_SHORT).show()
                } else if(ipCaptcha.text.toString() != captcha.text) {
                    Toast.makeText(applicationContext, "Invalid Captcha!", Toast.LENGTH_SHORT).show()
                }
                else {
                    val intent = Intent(applicationContext, MainActivity2::class.java)
                    intent.putExtra("mobNum", mobNum.text.toString())
                    startActivity(intent)
                }
            }
        }
    }
}