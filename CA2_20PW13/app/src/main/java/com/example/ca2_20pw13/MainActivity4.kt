package com.example.ca2_20pw13

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity4 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val ref = db.collection("Alumni")

        val details = findViewById<TextView>(R.id.details)
        val viewBtn = findViewById<Button>(R.id.viewBtn)

        viewBtn.setOnClickListener {
            val course = findViewById<EditText>(R.id.fetchcourse).text.toString()
            if(course == "") {
                Toast.makeText(baseContext, "Enter course", Toast.LENGTH_SHORT).show()
            }
            else {
                val query = ref.whereEqualTo("course", course)
                var text = ""

                query.get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            val role = document.getString("role")
                            text += "\nName: "+document.getString("name")
                            text += "\nRoll no: "+document.getString("rollNo")
                            text += "\nCourse: "+document.getString("course")
                            text += "\nBranch: "+document.getString("branch")
                            text += "\nYOJ: "+document.getString("yoj")
                            text += "\nRole: $role"

                            if(role == "studying") {
                                text += "\nCurrent course: "+document.getString("currentCourse")
                                text += "\nUniversity: "+document.getString("university")+"\n\n"
                            }
                            else {
                                text += "\nDesignation: "+document.getString("designation")
                                text += "\nCompany: "+document.getString("company")+"\n\n"
                            }
                        }
                        details.text = text
                    }
                    .addOnFailureListener { exception ->
                        println(exception)
                    }
            }
        }

    }
}