package com.example.datedifference

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Period
import java.time.ZoneId
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val formatDate = SimpleDateFormat("dd MMM YYYY", Locale.US)
        val from = findViewById<TextView>(R.id.from)
        val to = findViewById<TextView>(R.id.to)
        val diffButton = findViewById<Button>(R.id.button)
        val years = findViewById<TextView>(R.id.years)
        val months = findViewById<TextView>(R.id.months)
        val days = findViewById<TextView>(R.id.days)
        var fromDate = Date()
        var toDate = Date()

        from.setOnClickListener {
            val selected = Calendar.getInstance()

            val datePicker = DatePickerDialog(this,
                { _, year, month, day ->
                    selected.set(Calendar.YEAR, year)
                    selected.set(Calendar.MONTH, month)
                    selected.set(Calendar.DAY_OF_MONTH, day)
                    fromDate = selected.time
                    from.text = formatDate.format(selected.time)
                }, selected.get(Calendar.YEAR), selected.get(Calendar.MONTH), selected.get(Calendar.DAY_OF_MONTH));
            datePicker.show();
        }

        to.setOnClickListener {
            val selected = Calendar.getInstance()

            val datePicker = DatePickerDialog(this,
                { _, year, month, day ->
                    selected.set(Calendar.YEAR, year)
                    selected.set(Calendar.MONTH, month)
                    selected.set(Calendar.DAY_OF_MONTH, day)
                    toDate = selected.time
                    to.text = formatDate.format(selected.time)
                }, selected.get(Calendar.YEAR), selected.get(Calendar.MONTH), selected.get(Calendar.DAY_OF_MONTH));
            datePicker.show();
        }

        diffButton.setOnClickListener {
            val period = Period.between(fromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            years.text = period.years.toString()
            months.text = period.months.toString()
            days.text = period.days.toString()
        }
    }
}