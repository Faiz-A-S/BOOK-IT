package com.example.book_it

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainMenu: Fragment() {
    private var categoryAdapter: CategoryAdapter? = null

    //override fun onCreate(savedInstanceState: Bundle?) {
        //super.onCreate(savedInstanceState)
        //setContentView(R.layout.main_menu)

        //val rvCategory = findViewById<RecyclerView>(R.id.rv_menu)
        //categoryAdapter = CategoryAdapter(this,getBook())
        //rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //rvCategory.adapter=categoryAdapter
    //}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_menu, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val rvCategory = itemView.findViewById<RecyclerView>(R.id.rv_menu)
        categoryAdapter = context?.let { CategoryAdapter(it,getBook()) }
        rvCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
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