package com.example.ca120pw13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val captcha = findViewById<TextView>(R.id.textView6)
        captcha.text = (Random.nextInt(900000)+100000).toString()

        val re = Regex("^[6-9]{1}[0-9]{9}$")

        val submit = findViewById<Button>(R.id.button2)

        submit.setOnClickListener {
            val name = findViewById<EditText>(R.id.editText1).text.toString()
            val mobNum = findViewById<EditText>(R.id.editText2).text.toString()
            val ipCaptcha = findViewById<EditText>(R.id.editText3).text.toString()

            if(name == "" || mobNum == "" || ipCaptcha == "") {
                Toast.makeText(applicationContext, "Enter all inputs!", Toast.LENGTH_SHORT).show()
            }
            else {
                if(!mobNum.matches(re)) {
                    Toast.makeText(applicationContext, "Invalid mobile number!", Toast.LENGTH_SHORT).show()
                }
                else if(ipCaptcha != captcha.text) {
                    Toast.makeText(applicationContext, "Invalid Captcha!", Toast.LENGTH_SHORT).show()
                }
                else {
                    val intent = Intent(applicationContext, MainActivity3::class.java)
                    intent.putExtra("name", name)
                    intent.putExtra("mobNum", mobNum)
                    startActivity(intent)
                }
            }
        }
    }
}