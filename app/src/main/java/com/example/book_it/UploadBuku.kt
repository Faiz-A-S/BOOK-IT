package com.example.book_it

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.book_it.databinding.TambahBukuBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadBuku: AppCompatActivity(){

    private lateinit var dbRef: DatabaseReference
    private lateinit var binding: TambahBukuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TambahBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbRef = FirebaseDatabase.getInstance().getReference("Book")

        binding.imgbuttontambah.setOnClickListener{
            selectImage()
        }

        binding.tambahBuku.setOnClickListener{
            tambahBukuData()
        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        pickImage.launch(intent)
    }

    val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if(result.resultCode == RESULT_OK){
            val uriImage = result.data?.data
            binding.imgbuttontambah.setImageURI(uriImage)
        }
    }

    private fun tambahBukuData() {
        val judul = binding.judulBuku.text.toString()
        val harga = binding.hargaBuku.text.toString().toInt()
        val penulis = binding.penulisBuku.text.toString()
        val desk = binding.deskripsiBuku.text.toString()

        if(judul.isEmpty()){
            Toast.makeText(this,"Judul kosong",Toast.LENGTH_SHORT).show()
        }
        if(harga.toString().isEmpty()){
            Toast.makeText(this,"Harga kosong",Toast.LENGTH_SHORT).show()
        }
        if(penulis.isEmpty()){
            Toast.makeText(this,"Penulis kosong",Toast.LENGTH_SHORT).show()
        }
        if(desk.isEmpty()){
            Toast.makeText(this,"Deskripsi kosong",Toast.LENGTH_SHORT).show()
        }

        val bukuID = dbRef.push().key!!

        val uploadBukuBaru = UploadBukuModel(judul,harga,penulis,desk)
        dbRef.child(bukuID).setValue((uploadBukuBaru))
            .addOnCompleteListener{
                Toast.makeText(this,"Nice",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{err ->
        Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
    }
    }
}