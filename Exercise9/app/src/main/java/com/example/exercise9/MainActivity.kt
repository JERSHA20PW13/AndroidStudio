package com.example.exercise9

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import java.util.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var videoView = findViewById<VideoView>(R.id.videoView)
        val mediaController = MediaController(this)

        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.amongus));
        videoView.start();
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.hide -> {
                var videoView = findViewById<VideoView>(R.id.videoView)
                if(videoView.visibility == View.VISIBLE || videoView.isVisible) {
                    videoView.visibility = View.GONE
                    item.title = "unhide"
                }
                else {
                    videoView.visibility = View.VISIBLE
                    item.title = "hide"
                }
                return true
            }
            R.id.size -> {
                var textView = findViewById<TextView>(R.id.textView)
                if(item.title == "increase font size") {
                    textView.textSize = resources.getDimension(R.dimen.medium)
                    item.title = "decrease font size"
                }
                else {
                    textView.textSize = resources.getDimension(R.dimen.small)
                    item.title = "increase font size"
                }
                return true
            }
            R.id.color -> {
                var textView = findViewById<TextView>(R.id.textView)
                val rnd = Random()
                textView.setTextColor(
                    Color.argb(
                        255,
                        rnd.nextInt(256),
                        rnd.nextInt(256),
                        rnd.nextInt(256)
                    )
                )
                return true
            }
            R.id.play -> {
                var videoView = findViewById<VideoView>(R.id.videoView)
                videoView.start()
                item.title = "playing"
                return true
            }
            R.id.exit -> {
                this@MainActivity.finish()
                exitProcess(0)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}