package com.example.book_it

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.book_it.databinding.TambahBukuBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.util.*

class UploadBuku: AppCompatActivity(){
    lateinit var storage: StorageReference
    private lateinit var dbRef: DatabaseReference
    private lateinit var binding: TambahBukuBinding
    private lateinit var uriImage: Uri
    private lateinit var uri: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storage = FirebaseStorage.getInstance().getReference("Cover")
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

    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if(result.resultCode == RESULT_OK){
            uriImage = result.data?.data!!
            binding.imgbuttontambah.setImageURI(uriImage)
            val sd = getFileName(applicationContext, uriImage)
            val storageRef = storage.child("coverbuku/$sd")
            var uploadTask = storageRef.putFile(uriImage)

            uri = uploadTask.snapshot.storage.downloadUrl.toString()
        }
    }

    private fun tambahBukuData() {
        //val gambar
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
        val uploadBukuBaru = UploadBukuModel(uri, judul, harga, penulis, desk)
        //val uploadBukuBaru = UploadBukuModel(storage.downloadUrl.toString(),judul,harga,penulis,desk)
        dbRef.child(bukuID).setValue((uploadBukuBaru))
            .addOnCompleteListener{
                Toast.makeText(this,"Nice",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{err ->
        Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
    }
    }
    @SuppressLint("Range")
    private fun getFileName(context: Context, uri: Uri): String? {
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor.use {
                if (cursor != null) {
                    if(cursor.moveToFirst()) {
                        return cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    }
                }
            }
        }
        return uri.path?.lastIndexOf('/')?.let { uri.path?.substring(it) }
    }
}