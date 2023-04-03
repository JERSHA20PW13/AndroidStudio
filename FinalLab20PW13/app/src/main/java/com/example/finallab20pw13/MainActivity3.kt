package com.example.finallab20pw13

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity3 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val querybutton = findViewById<Button>(R.id.query)
        val nextbutton = findViewById<Button>(R.id.next)

        querybutton.setOnClickListener {
            val queryBloodGroup = findViewById<EditText>(R.id.querybg).text.toString()

            if(queryBloodGroup == "") {
                Toast.makeText(applicationContext, "Enter blood group!", Toast.LENGTH_LONG).show()
            }
            else {
                var result = "";
                val resultView = findViewById<TextView>(R.id.result)

                val ref = db.collection("donors")
                val query = ref.whereEqualTo("bloodgroup", queryBloodGroup)
                query.get()
                    .addOnSuccessListener { rows ->
                        for (row in rows) {
                            result += "\n\nName: "+row.getString("name")
                            result += "\nPhone no: "+row.getString("phoneno")
                            result += "\nAddress: "+row.getString("address")
                            result += "\nBlood group: "+row.getString("bloodgroup")
                        }
                        resultView.text = result
                    }
                    .addOnFailureListener {
                        println("Error!");
                    }
            }
        }

        nextbutton.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity4::class.java)
            startActivity(intent)
        }
    }
}