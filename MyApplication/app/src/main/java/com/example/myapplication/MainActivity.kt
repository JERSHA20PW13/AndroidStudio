package com.example.myapplication

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DOCUMENT_ID = "com.example.myapplication.DOCUMENT_ID"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)

        val date = findViewById<TextView>(R.id.date)
        val fromTime = findViewById<TextView>(R.id.fromTime)
        val toTime = findViewById<TextView>(R.id.toTime)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        var noOfKids = "1 Kid";
        val location = findViewById<EditText>(R.id.location)
        val phoneNum = findViewById<EditText>(R.id.phNum)
        val btn = findViewById<Button>(R.id.sendBtn)

        date.setOnClickListener{
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                val selectDate: Calendar = Calendar.getInstance()
                selectDate.set(Calendar.YEAR, year)
                selectDate.set(Calendar.MONTH, month)
                selectDate.set(Calendar.DAY_OF_MONTH, day)
                date.text = "  $year/$month/$day";
            }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        fromTime.setOnClickListener {
            getTimePicker(fromTime)
        }

        toTime.setOnClickListener {
            getTimePicker(toTime)
        }

        radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            val checkedRadioButton = radioGroup.findViewById<RadioButton>(checkedId)
            noOfKids = checkedRadioButton.text.toString()
        }

        btn.setOnClickListener {
            val dt = date.text.toString()
            val fromT = fromTime.text.toString()
            val toT = toTime.text.toString()
            val loc = location.text.toString()
            val mobNum = phoneNum.text.toString()

            if(dt == "" || fromT == "" || toT == "" || loc == "" || mobNum == "" || noOfKids == "") {
                Toast.makeText(applicationContext, "Enter all inputs!", Toast.LENGTH_SHORT).show()
            }
            else if(!Patterns.PHONE.matcher(mobNum).matches()) {
                Toast.makeText(applicationContext, "Invalid mobile number!", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(applicationContext, "Your details saved!", Toast.LENGTH_SHORT).show()

                val db: FirebaseFirestore = FirebaseFirestore.getInstance()

                val data = hashMapOf(
                    "date" to dt,
                    "fromTime" to fromT,
                    "toTime" to toT,
                    "noOfKids" to noOfKids,
                    "location" to loc,
                    "mobileNum" to mobNum
                )

                db.collection("/Request")
                    .add(data)
                    .addOnSuccessListener { documentReference ->
                        var documentId = documentReference.id

                        val intent = Intent(applicationContext, MainActivity2::class.java)
                        intent.putExtra(EXTRA_DOCUMENT_ID, documentId)

                        println("KEY_START: $documentId")

                        val pendingIntent: PendingIntent =
                            PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

                        val mChannel = NotificationChannel("Babysit", "NotificationChannel", NotificationManager.IMPORTANCE_DEFAULT)
                        val builder = Notification.Builder(
                            applicationContext,
                            "Babysit"
                        )
                            .setSmallIcon(R.drawable.baby)
                            .setContentTitle("Babysit")
                            .setContentText("Yay! Request made!")
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)

                        val notificationManager: NotificationManager =
                            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.createNotificationChannel(mChannel)
                        notificationManager.notify(401, builder.build())
                    }
                    .addOnFailureListener {
                        println("Failure :(")
                    }
            }
        }
    }

    private fun getTimePicker(field:TextView) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                var hour = hourOfDay.toString()
                var min = minute.toString()
                if (hourOfDay < 10) {
                    hour = "0$hourOfDay"
                }
                if (minute < 10) {
                    min = "0$minute"
                }
                field.text = "  $hour :$min"
            },
            hour,
            minute,
            false
        )
        timePickerDialog.show()
    }
}
