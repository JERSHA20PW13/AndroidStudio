package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.MainActivity.Companion.EXTRA_DOCUMENT_ID
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val entry = findViewById<TextView>(R.id.entry)
        var display = ""
        val documentId = intent.getStringExtra(EXTRA_DOCUMENT_ID)
        val db = FirebaseFirestore.getInstance()

        if (documentId != null) {
            db.collection("Request").document(documentId)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        display += "Date: " + document.getString("date")
                        display += "\nFrom Time: " + document.getString("fromTime")
                        display += "\nTo Time: " + document.getString("toTime")
                        display += "\nNumber of kids: " + document.getString("noOfKids")
                        display += "\nLocation: " + document.getString("location")
                        display += "\nMobile Number: " + document.getString("mobileNum")

                        entry.text = display

                    } else {
                        println("No such document!")
                    }
                }
                .addOnFailureListener {
                    println("Error :(")
                }
        }
        else {
            println("Document Id not found :(")
        }
    }
}

