package com.example.biodata

import android.app.DatePickerDialog
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fName = findViewById<EditText>(R.id.fName)
        val lName = findViewById<EditText>(R.id.lName)
        val phone = findViewById<EditText>(R.id.Phone)
        val email = findViewById<EditText>(R.id.Email)
        val birthday = findViewById<TextView>(R.id.Birthday)
        val gender = findViewById<Spinner>(R.id.spinner)
        val address = findViewById<EditText>(R.id.Address)
        val button = findViewById<Button>(R.id.button)
        val calendarBtn = findViewById<ImageButton>(R.id.imageButton)

        fName.requestFocus()

        calendarBtn.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(

                this,
                { view, year, monthOfYear, dayOfMonth ->
                    birthday.setText(
                        (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year))
                },
                year,
                month,
                day
            )
            datePickerDialog.datePicker.maxDate = Date().time
            datePickerDialog.show()
        }

        button.setOnClickListener {

            val pattern = Regex("^([+][9][1]|[9][1]|[0]){0,1}([7-9]{1})([0-9]{9})$")
            val gmail = Regex("[a-zA-Z0-9._-]+@[a-z]+(\\.+[a-z]+)+")

            if (fName.text.toString() == "" || lName.text.toString() == "" || address.text.toString() == "")
                Toast.makeText(applicationContext, "Input field empty", Toast.LENGTH_SHORT).show()
            else if (!phone.text.toString().matches(pattern))
                Toast.makeText(applicationContext, "Invalid mobile number", Toast.LENGTH_SHORT).show()
            else if (!email.text.toString().matches(gmail))
                Toast.makeText(applicationContext, "Invalid email number", Toast.LENGTH_SHORT).show()
            else {
                val details = "First Name: " + fName.text.toString() +
                        "\n\nLast Name: " + lName.text.toString() +
                        "\n\nPhone Number: " + phone.text.toString() +
                        "\n\nEmail: " + email.text.toString() +
                        "\n\nBirthday: " + birthday.text.toString() +
                        "\n\nGender: " + gender.selectedItem.toString() +
                        "\n\nAddress: " + address.text.toString()

                val intent = Intent(applicationContext, MainActivity2::class.java)

                intent.putExtra("Details", details)
                intent.putExtra("FName", fName.text.toString())

                startActivity(intent)

            }
        }
    }
}