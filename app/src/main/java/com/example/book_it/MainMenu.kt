package com.example.book_it

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

class MainMenu: AppCompatActivity() {
    private var categoryAdapter: CategoryAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        val rvCategory = findViewById<RecyclerView>(R.id.rv_menu)
        categoryAdapter = CategoryAdapter(this,getBook())
        rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvCategory.adapter=categoryAdapter
    }
    private fun getBook(): List<Category> {
        val listCategory = ArrayList<Category>()
        val listBook = ArrayList<Book>()
        listBook.add(Book(R.drawable.book1, "Book1", "14000"))
        listBook.add(Book(R.drawable.book2, "Book2", "11000"))
        listBook.add(Book(R.drawable.book3, "Book3", "10000"))
        listBook.add(Book(R.drawable.book4, "Book4", "9000"))
        listCategory.add(Category("Category 1", listBook))

        val listBook2 = ArrayList<Book>()
        listBook2.add(Book(R.drawable.book4, "Book4", "9000"))
        listBook2.add(Book(R.drawable.book1, "Book1", "14000"))
        listBook2.add(Book(R.drawable.book2, "Book2", "11000"))
        listBook2.add(Book(R.drawable.book3, "Book3", "10000"))

        listCategory.add(Category("Category 2", listBook2))

        return listCategory
    }
}