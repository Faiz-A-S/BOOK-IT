package com.example.book_it

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.book_it.CategoryAdapter.CategoryViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.TextView

class CategoryAdapter(private val context: Context, private val categoryList:List<Category>) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.book_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.category_Name.text = category.nameCategory
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        holder.recycle_Book.layoutManager = linearLayoutManager
        val bookAdapter = BookAdapter()
        bookAdapter.setData(category.listBook)
        holder.recycle_Book.adapter = bookAdapter
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category_Name: TextView = itemView.findViewById(R.id.category_name)
        val recycle_Book: RecyclerView = itemView.findViewById(R.id.rv_book)

    }
}