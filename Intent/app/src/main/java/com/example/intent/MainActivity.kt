package com.example.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val continueBtn = findViewById<Button>(R.id.button)
        val cancelBtn = findViewById<Button>(R.id.button2)

        val firstName = findViewById<EditText>(R.id.firstName)
        val lastName = findViewById<EditText>(R.id.lastName)
        val visitType = findViewById<RadioGroup>(R.id.radioGroup)
        val country = findViewById<Spinner>(R.id.spinner)

        continueBtn.setOnClickListener {
            val typeId = visitType!!.checkedRadioButtonId
            val type = findViewById<RadioButton>(typeId)?.text?.toString().orEmpty()

            val pattern = Regex("[a-zA-z\\s]+")

            if(firstName.text.toString().matches(pattern) && lastName.text.toString().matches(pattern)) {

                val details = "First Name: " + firstName.text.toString() +
                        "\nLast Name: " + lastName.text.toString() +
                        "\nVisit Type: " + type +
                        "\nCountry: " + country.selectedItem.toString()

                val intent = Intent(applicationContext, MainActivity2::class.java)

                intent.putExtra("Message", details)
                startActivity(intent)
            }
            else {
                Toast.makeText(this@MainActivity, "Names should contain only letter", Toast.LENGTH_SHORT).show()
            }

        }

        cancelBtn.setOnClickListener {
            firstName.setText("")
            lastName.setText("")
            visitType.clearCheck()
            country.setSelection(0)
        }
    }
}