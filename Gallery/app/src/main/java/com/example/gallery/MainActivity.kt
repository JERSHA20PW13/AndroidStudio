package com.example.gallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var gridView = findViewById<GridView>(R.id.gridView)
        val videoModelArrayList: ArrayList<Video> = ArrayList<Video>()

        videoModelArrayList.add(Video("Among us", R.drawable.among_us))
        videoModelArrayList.add(Video("Game", R.drawable.among_us))
        videoModelArrayList.add(Video("Sus", R.drawable.among_us))
        videoModelArrayList.add(Video("Mobi game", R.drawable.among_us))
        videoModelArrayList.add(Video("Play", R.drawable.among_us))

        val adapter = Adapter(this, videoModelArrayList)
        gridView.adapter = adapter

        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
            val intent = Intent(applicationContext, VideoActivity::class.java);
            intent.putExtra("videoId", R.raw.amongus);
            startActivity(intent);
        }
    }
}