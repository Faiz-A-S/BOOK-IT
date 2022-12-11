package com.example.book_it

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.Exception
import android.content.Intent
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    //base buat nampung fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base)
        loadFragment(MainMenu())
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main_menu -> {
                    loadFragment(MainMenu())
                    return@OnItemSelectedListener true
                }
                R.id.chat -> {
                    loadFragment(ChatFragment())
                    return@OnItemSelectedListener true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    return@OnItemSelectedListener true
                }
            }
            false
        })
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }
}