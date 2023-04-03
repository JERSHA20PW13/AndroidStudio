package com.example.menu

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val popup = findViewById<TextView>(R.id.textView)

        popup.setOnClickListener {
            val popupMenu = PopupMenu(this@MainActivity, popup)

            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                val text = findViewById<TextView>(R.id.textView)

                if(menuItem.title == "Small") {
                    val fontSize = resources.getDimension(R.dimen.small)
                    text.setTextSize(fontSize)
                }
                else if(menuItem.title == "Medium") {
                    val fontSize = resources.getDimension(R.dimen.medium)
                    text.setTextSize(fontSize)
                }
                else if(menuItem.title == "Large") {
                    val fontSize = resources.getDimension(R.dimen.large)
                    text.setTextSize(fontSize)
                }
                else if(menuItem.title == "Change Color") {
                    val rnd = Random()
                    text.setTextColor(
                        Color.argb(
                            255,
                            rnd.nextInt(256),
                            rnd.nextInt(256),
                            rnd.nextInt(256)
                        )
                    )
                }
                true
            }

            popupMenu.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mymenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var bg = findViewById<ConstraintLayout>(R.id.bg)

        return when (item.itemId) {
            R.id.color1 -> {
                bg.setBackgroundResource(R.color.red)
                true
            }
            R.id.color2 -> {
                bg.setBackgroundResource(R.color.green)
                return true
            }
            R.id.color3 -> {
                bg.setBackgroundResource(R.color.yellow)
                return true
            }
            R.id.color4 -> {
                bg.setBackgroundResource(R.color.blue)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}