package com.example.book_it

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.book_it.databinding.RegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register: AppCompatActivity() {
    private lateinit var binding : RegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = RegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.backtologin.setOnClickListener {
            intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        binding.registerButton.setOnClickListener{
            val email = binding.registerEmail.text.toString()
            val password = binding.registerPassword.text.toString()
            val confirmpass = binding.registerPassword2.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && confirmpass.isNotEmpty()){
                if(password == confirmpass){
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)

                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }}

                }else{
                    Toast.makeText(this,"Password tidak sama", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "Isi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}