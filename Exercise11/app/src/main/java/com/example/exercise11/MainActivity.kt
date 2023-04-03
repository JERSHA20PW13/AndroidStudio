package com.example.exercise11

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = DBHandler(this, null)

        val insert = findViewById<Button>(R.id.insert)
        val delete = findViewById<Button>(R.id.delete)
        val update = findViewById<Button>(R.id.update)
        val view = findViewById<Button>(R.id.view)
        val viewall = findViewById<Button>(R.id.viewall)

        insert.setOnClickListener {
            val rno = findViewById<EditText>(R.id.rno).text.toString()
            val name = findViewById<EditText>(R.id.name).text.toString()
            val marks = findViewById<EditText>(R.id.marks).text.toString()

            if(rno == "" || name == "" || marks == "") {
                showPopUpWindow("Error", "Please enter all values", it)
            }
            else {
                db.addEntry(rno, name, marks.toInt());
                showPopUpWindow("Success", "Record added", it)
            }
        }

        delete.setOnClickListener {
            val rno = findViewById<EditText>(R.id.rno).text.toString()
            if(rno == "") {
                showPopUpWindow("Error", "Please enter Rollno", it)
            }
            else {
                if(db.getEntry(rno) == null) {
                    showPopUpWindow("Error", "No records found", it)
                }
                else {
                    db.deleteEntry(rno)
                    showPopUpWindow("Success", "Record Deleted", it)
                }
            }
        }

        update.setOnClickListener {
            val rno = findViewById<EditText>(R.id.rno).text.toString()
            val name = findViewById<EditText>(R.id.name).text.toString()
            val marks = findViewById<EditText>(R.id.marks).text.toString()

            if(rno == "") {
                showPopUpWindow("Error", "Please enter Rollno", it)
            }
            else {
                if(db.getEntry(rno) == null) {
                    showPopUpWindow("Error", "No records found", it)
                }
                else {
                    db.updateEntry(rno, name, marks)
                    showPopUpWindow("Success", "Record Modified", it)
                }
            }
        }

        view.setOnClickListener {
            val rno = findViewById<EditText>(R.id.rno).text.toString()

            if(rno == "") {
                showPopUpWindow("Error", "Please enter Rollno", it)
            }
            else {
                var entry = db.getEntry(rno)
                showPopUpWindow("Student Details", entry.toString(), it)
            }
        }

        viewall.setOnClickListener {
            var entries = db.getEntries()
            showPopUpWindow("Student Details", entries.toString(), it)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun showPopUpWindow(title: String, content: String, it: View) {
        val popupView = layoutInflater.inflate(R.layout.popup, null).apply {
            findViewById<TextView>(R.id.popupTitle).text = title
            findViewById<TextView>(R.id.popupMsg).text = content
        }
        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popupWindow.showAtLocation(it, Gravity.CENTER, 0, 0)

        popupView.setOnTouchListener { _, _ ->
            popupWindow.dismiss()
            true
        }
    }
}