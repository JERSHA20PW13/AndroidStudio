package com.example.finallab20pw13

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        val registerBtn = findViewById<Button>(R.id.register)

        registerBtn.setOnClickListener {
            val name = findViewById<EditText>(R.id.name).text.toString()
            val phoneno = findViewById<EditText>(R.id.phoneno).text.toString()
            val address = findViewById<EditText>(R.id.address).text.toString()
            val bloodgroup = findViewById<EditText>(R.id.bloodgroup).text.toString()

            if(name == "" || phoneno == "" || address == "" || bloodgroup == "") {
                Toast.makeText(applicationContext, "Enter all input!", Toast.LENGTH_LONG).show()
            }
            else {

                val data = hashMapOf(
                    "name" to name,
                    "phoneno" to phoneno,
                    "address" to address,
                    "bloodgroup" to bloodgroup
                )

                db.collection("donors")
                    .add(data)
                    .addOnSuccessListener {
                        Toast.makeText(applicationContext, "Your details saved!", Toast.LENGTH_LONG).show()
                        val intent = Intent(applicationContext, MainActivity3::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener{
                        println("Error!");
                    }
            }
        }
    }
}