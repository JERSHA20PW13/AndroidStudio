package com.example.ca2_20pw13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val signin = findViewById<Button>(R.id.signin)
        val register = findViewById<Button>(R.id.add)

        auth = Firebase.auth

        signin.setOnClickListener {
            val email = findViewById<EditText>(R.id.email).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()

            if(email == "" || password == "") {
                Toast.makeText(baseContext, "Enter all inputs!", Toast.LENGTH_SHORT).show()
            }
            else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser

                            val intent = Intent(applicationContext, MainActivity4::class.java)
                            startActivity(intent)

                        } else {
                            Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        register.setOnClickListener {
            var email = findViewById<EditText>(R.id.email).text.toString()
            var password = findViewById<EditText>(R.id.password).text.toString()

            if(email == "" || password == "") {
                Toast.makeText(baseContext, "Enter all inputs!", Toast.LENGTH_SHORT).show()
            }
            else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            Toast.makeText(baseContext, "Registered!", Toast.LENGTH_SHORT).show()

                            val intent = Intent(applicationContext, MainActivity4::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

//    public override fun onStart() {
//        super.onStart()
//        val currentUser = auth.currentUser
//        if(currentUser != null){
//            val intent = Intent(applicationContext, MainActivity4::class.java)
//            startActivity(intent)
//        }
//    }
}