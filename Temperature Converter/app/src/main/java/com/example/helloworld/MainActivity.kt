package com.example.helloworld

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)
        val fahrenheitET = findViewById<EditText>(R.id.fahrenheit)
        val celsiusET = findViewById<EditText>(R.id.celsius)

        btn.setOnClickListener {

            if(fahrenheitET.isFocused) {
                if(fahrenheitET.text.toString() == "")
                    Toast.makeText(this@MainActivity, "Give Input", Toast.LENGTH_SHORT).show()
                else {
                    val celsius = ((fahrenheitET.text.toString().toDouble() - 32) * 5) / 9

                    celsiusET.setText(celsius.toString())

                    Toast.makeText(this@MainActivity, celsius.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            else {

                if(celsiusET.text.toString() == "")
                    Toast.makeText(this@MainActivity, "Give Input", Toast.LENGTH_SHORT).show()
                else {
                    val fahrenheit = celsiusET.text.toString().toDouble() * 9 / 5 + 32

                    fahrenheitET.setText(fahrenheit.toString())

                    Toast.makeText(this@MainActivity, fahrenheit.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }
}