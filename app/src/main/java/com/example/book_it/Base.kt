package com.example.book_it

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import java.lang.Exception

class Base : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    //base buat nampung fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base)
        loadFragment(Chat())
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main_menu -> {
                    loadFragment(MainMenu())
                    return@OnItemSelectedListener true
                }
                R.id.chat -> {
                    loadFragment(Chat())
                    return@OnItemSelectedListener true
                }
                R.id.profile -> {
                    loadFragment(Account())
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