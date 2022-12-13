package com.example.book_it

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.book_it.databinding.RegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference

class Register: AppCompatActivity() {
    private lateinit var binding : RegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    fun isEmail(input: String): Boolean {
        // Set up the regular expression to match email addresses
        val regex = Regex("^[A-Za-z0-9+_.-]+@(.+)$")

        // Use the `matches` method to check if the input string matches the regular expression
        return regex.matches(input)
    }

    fun registerUser(email:String,password:String){
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("users")

        val user = User(email, password) // create a new user object
        ref.push().setValue(user) // save the user to the "users" node in the database
    }

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
                if(isEmail(email)==true){
                    if(password == confirmpass) {
                        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    val intent = Intent(this, Login::class.java)
                                    registerUser(email,password)
                                    startActivity(intent)

                                } else {
                                    Toast.makeText(
                                        this,"Registrasi berhasil", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }else{
                        Toast.makeText(this,"Password tidak sama", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"Email tidak sesuai", Toast.LENGTH_SHORT).show()
            }
            }else {
                Toast.makeText(this, "Isi semua field", Toast.LENGTH_SHORT).show()
        }
    }
}}