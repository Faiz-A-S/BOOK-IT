package com.example.book_it

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.book_it.databinding.ActivityMainBinding

class UploadBuku: AppCompatActivity(){

    private val my_request_code: Int = 0
    private lateinit var imageView: ImageView
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambah_buku)

    }
}