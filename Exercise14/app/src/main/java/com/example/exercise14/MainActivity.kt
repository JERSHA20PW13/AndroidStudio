package com.example.exercise14

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

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

                            val intent = Intent(applicationContext, MainActivity2::class.java)
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
                            val intent = Intent(applicationContext, MainActivity2::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            val intent = Intent(applicationContext, MainActivity2::class.java)
            intent.putExtra("userID", currentUser.uid)
            startActivity(intent)
        }
    }
}
