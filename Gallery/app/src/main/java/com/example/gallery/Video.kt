package com.example.gallery

class Video(private var title: String, private var imgid: Int) {

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getImgid(): Int {
        return imgid
    }

    fun setImgid(imgid: Int) {
        this.imgid = imgid
    }
}

