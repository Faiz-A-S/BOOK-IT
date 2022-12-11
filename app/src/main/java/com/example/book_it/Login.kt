package com.example.book_it

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.login_button)
        button.setOnClickListener {
            intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonRegister = findViewById<TextView>(R.id.register_button)
        buttonRegister.setOnClickListener {
            intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}