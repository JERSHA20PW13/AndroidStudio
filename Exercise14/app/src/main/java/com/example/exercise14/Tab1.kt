package com.example.exercise14

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Tab1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Tab1 : Fragment() {
    lateinit var auth: FirebaseAuth

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val deadline = view.findViewById<TextView>(R.id.date)
        val addBtn = view.findViewById<Button>(R.id.add)

        deadline.setOnClickListener {
            val c = Calendar.getInstance()

            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
                    { _, year, monthOfYear, dayOfMonth ->
                        deadline.text =
                            (year.toString() + "/" + (monthOfYear + 1) + "/" + dayOfMonth.toString())
                    },
                    year,
                    month,
                    day
                )
            }?.show()
        }

        addBtn.setOnClickListener {
            val title = view.findViewById<EditText>(R.id.tasktitle).text.toString()
            val desc = view.findViewById<EditText>(R.id.desc).text.toString()
            val deadline = view.findViewById<TextView>(R.id.date).text.toString()

            if(title == "" || desc == "" || deadline == "") {
                Toast.makeText(context, "Enter all inputs!", Toast.LENGTH_SHORT).show()
            }
            else {
                val db: FirebaseFirestore = FirebaseFirestore.getInstance()
                auth = Firebase.auth
                val user = auth.currentUser

                if(user != null) {
                    val data = hashMapOf(
                        "userID" to user.uid,
                        "title" to title,
                        "description" to desc,
                        "deadline" to deadline,
                        "status" to "in process"
                    )

                    db.collection("/Tasks")
                        .add(data)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Task added!", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            println("Failure :(")
                        }
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Tab1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Tab1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}