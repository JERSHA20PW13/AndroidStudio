package com.example.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class Adapter(context: Context, videoModelArrayList: ArrayList<Video>) :
    ArrayAdapter<Video?>(context, 0, videoModelArrayList!! as List<Video?>) {

    var layoutInflater: LayoutInflater? = null
    lateinit var videoTV: TextView
    lateinit var videoIV: ImageView

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val videoModel: Video? = getItem(position)
        var convertView = convertView

        if(layoutInflater == null) {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if(convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.grid_item, null)
        }

        videoIV = convertView!!.findViewById(R.id.imageView)
        videoTV = convertView!!.findViewById(R.id.textView)

        if (videoModel != null) {
            videoIV.setImageResource(videoModel.getImgid())
        }

        if (videoModel != null) {
            videoTV.text = videoModel.getTitle()
        }

        return convertView

    }
}