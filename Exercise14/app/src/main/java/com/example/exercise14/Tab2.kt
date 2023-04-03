package com.example.exercise14

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDate


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

interface Interface {
    fun refresh()
}

/**
 * A simple [Fragment] subclass.
 * Use the [Tab2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Tab2 : Fragment() {
    lateinit var auth: FirebaseAuth
    private var interfaceObj: Interface? = null

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

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayTasks(documents: QuerySnapshot?): Any? {
        val inflation = layoutInflater

        if (documents != null) {
            val dateFormat = SimpleDateFormat("yyyy/MM/dd")
            val date2 = dateFormat.parse(LocalDate.now().toString().replace("-", "/"))

            for (document in documents) {
                val taskview = inflation.inflate(R.layout.task, null)
                taskview.findViewById<TextView>(R.id.tasktitle).text = document.getString("title")
                taskview.findViewById<TextView>(R.id.taskdesc).text = document.getString("description")
                taskview.findViewById<TextView>(R.id.deadline).text = "Deadline\n" + document.getString("deadline")
//                taskview.findViewById<TextView>(R.id.id).visibility = View.INVISIBLE
                taskview.findViewById<TextView>(R.id.id).text = document.reference.id.toString()

                val date1 = dateFormat.parse(document.getString("deadline"))

                var diff = date1.time - date2.time
                diff /= (1000 * 60 * 60 * 24)

                val tick = taskview.findViewById<ImageView>(R.id.tick)

                if(diff < 0.0) {
                    tick.setImageResource(R.drawable.redtick)
                }
                else {
                    val progress = document.getString("status")
                    if(progress.equals("in process")) {
                        tick.setImageResource(R.drawable.whitetick)
                    }
                    else {
                        tick.setImageResource(R.drawable.greentick)
                    }
                }

                val popupview = context?.let { Dialog(it) }
                popupview?.setContentView(R.layout.popup)

                taskview.setOnClickListener {
                    popupview?.show()
                }

                val delBtn = popupview?.findViewById<Button>(R.id.delete_button)
                val updateBtn = popupview?.findViewById<Button>(R.id.update_button)
                val db: FirebaseFirestore = FirebaseFirestore.getInstance()

                delBtn?.setOnClickListener {
                    val id = document.reference.id
                    db.collection("Tasks").document(id)
                        .delete()
                        .addOnSuccessListener {
                            Toast.makeText(context, "Task deleted!", Toast.LENGTH_SHORT).show()
                            popupview.dismiss()
                            interfaceObj?.refresh()
                        }
                        .addOnFailureListener {
                            println("Deletion failed!")
                        }
                }

                updateBtn?.setOnClickListener {
                    val title = popupview.findViewById<EditText>(R.id.updated_title).text.toString()
                    val desc = popupview.findViewById<EditText>(R.id.updated_desc).text.toString()
                    val status = popupview.findViewById<EditText>(R.id.status).text.toString()

                    var data = hashMapOf<String, Any>()
                    var flag = false

                    if(title != "") {
                        data["title"] = title
                        flag = true
                    }
                    else if(desc != "") {
                        data["description"] = desc
                        flag = true
                    }
                    else if(status != "") {
                        data["status"] = status
                        flag = true
                    }
                    else {
                        Toast.makeText(context, "Give input to update", Toast.LENGTH_LONG).show()
                    }

                    if(flag) {
                        val id = document.reference.id
                        db.collection("Tasks").document(id)
                            .update(data)
                            .addOnSuccessListener {
                                Toast.makeText(context, "Task updated!", Toast.LENGTH_SHORT).show()
                                popupview.dismiss()
                                interfaceObj?.refresh()
                            }
                            .addOnFailureListener {
                                println("Update failed!")
                            }
                    }
                }

                view?.findViewById<LinearLayout>(R.id.taskpanel)?.addView(taskview)
            }
        }
        return null;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val ref = db.collection("Tasks")

        auth = Firebase.auth
        val user = auth.currentUser

        val refreshBtn = view.findViewById<ImageView>(R.id.refreshbutton)
        val logoutBtn = view.findViewById<ImageView>(R.id.logoutbutton)

        refreshBtn.setOnClickListener {
            interfaceObj?.refresh()
        }

        logoutBtn.setOnClickListener {
            auth.signOut()
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

        if(user != null) {
            val query = ref.whereEqualTo("userID", user.uid)

            query.get()
                .addOnSuccessListener { documents ->
                    displayTasks(documents)
                }
                .addOnFailureListener { exception ->
                    println(exception)
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab2, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Interface) {
            interfaceObj = context
        } else {
            throw RuntimeException("$context must implement MyInterface")
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Tab2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Tab2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

