package com.example.book_it

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.book_it.BookAdapter.BookViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class BookAdapter : RecyclerView.Adapter<BookViewHolder>() {
    private var books: List<Book>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_satuan, parent, false)

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books!![position]
        holder.book_Cover.setImageResource(book.coverId)
        holder.book_Title.text = book.title
        holder.book_Price.text = book.price

       //holder.itemView.setOnClickListener{
       //     val intent = Intent(holder.itemView.context, BookDetail::class.java)
       //     holder.itemView.context.startActivity(intent)
       // }
        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Tombol telah berhasil diklik",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return books!!.size
    }

    fun setData(listBook: List<Book>) {
        this.books = listBook
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val book_Cover: ImageView= itemView.findViewById(R.id.book_cover)
        val book_Title: TextView= itemView.findViewById(R.id.book_title)
        val book_Price: TextView= itemView.findViewById(R.id.book_price)
    }
}