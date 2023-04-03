package com.example.ca2_20pw13

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity3 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val studyingFieldsLayout = findViewById<LinearLayout>(R.id.studying_fields_layout);
        val workingFieldsLayout = findViewById<LinearLayout>(R.id.working_fields_layout);

        var role = ""

        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.studying_radio_button -> {
                    studyingFieldsLayout.visibility = LinearLayout.VISIBLE
                    workingFieldsLayout.visibility = LinearLayout.GONE
                    role = "studying"
                }
                R.id.working_radio_button -> {
                    studyingFieldsLayout.visibility = LinearLayout.GONE
                    workingFieldsLayout.visibility = LinearLayout.VISIBLE
                    role = "working"
                }
            }
        }

        val registerBtn = findViewById<Button>(R.id.registerBtn)

        registerBtn.setOnClickListener {
            val name = findViewById<EditText>(R.id.name).text.toString()
            val rno = findViewById<EditText>(R.id.rno).text.toString()
            val course = findViewById<EditText>(R.id.course).text.toString()
            val branch = findViewById<EditText>(R.id.branch).text.toString()
            val yoj = findViewById<EditText>(R.id.yoj).text.toString()

            val curCourse = findViewById<EditText>(R.id.course_edit_text).text.toString()
            val university = findViewById<EditText>(R.id.university_edit_text).text.toString()
            val designation = findViewById<EditText>(R.id.designation_edit_text).text.toString()
            val company = findViewById<EditText>(R.id.company_edit_text).text.toString()

            var flag = false

            val data = HashMap<String, Any>()

            if(role == "studying") {
                if(name == "" || rno == "" || course == "" || branch == "" || yoj == "" || curCourse == "" || university == "") {
                    Toast.makeText(applicationContext, "Enter all inputs!", Toast.LENGTH_LONG).show()
                }
                else {
                    flag = true

                    data["name"] = name
                    data["rollNo"] = rno
                    data["course"] = course
                    data["branch"] = branch
                    data["yoj"] = yoj
                    data["role"] = role
                    data["currentCourse"] = curCourse
                    data["university"] = university

                }
            }
            else {
                if(name == "" || rno == "" || course == "" || branch == "" || yoj == "" || designation == "" || company == "") {
                    Toast.makeText(applicationContext, "Enter all inputs!", Toast.LENGTH_LONG).show()
                }
                else {
                    flag = true

                    data["name"] = name
                    data["rollNo"] = rno
                    data["course"] = course
                    data["branch"] = branch
                    data["yoj"] = yoj
                    data["role"] = role
                    data["designation"] = designation
                    data["company"] = company
                }
            }

            if(flag) {
                val db: FirebaseFirestore = FirebaseFirestore.getInstance()

                db.collection("Alumni")
                    .add(data)
                    .addOnSuccessListener {
                        Toast.makeText(applicationContext, "Details stored!", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        println("Failure :(")
                    }
            }
        }
    }
}