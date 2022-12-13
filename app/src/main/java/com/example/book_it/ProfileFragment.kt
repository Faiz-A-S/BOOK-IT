package com.example.book_it

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private var categoryAdapter: CategoryAdapter? = null
    private lateinit var auth: FirebaseAuth
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {
        super.onViewCreated(itemView,savedInstanceState)
        val rvCategory = itemView.findViewById<RecyclerView>(R.id.rv_profile)
        categoryAdapter = context?.let { CategoryAdapter(it,getBook()) }
        rvCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvCategory.adapter=categoryAdapter

        val tambahButton = itemView.findViewById<Button>(R.id.tambah_button)
        tambahButton.setOnClickListener{
            var intent = Intent(itemView.context, UploadBuku::class.java)
            startActivity(intent)
        }
        val keluarButton = itemView.findViewById<Button>(R.id.logout)
        keluarButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(itemView.context,"Bye bye...", Toast.LENGTH_SHORT).show()
            val intent = Intent(itemView.context, Login::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun getBook(): List<Category> {
        val listCategory = ArrayList<Category>()
        val listBook = ArrayList<Book>()
        listBook.add(Book(R.drawable.book1, "Book1", "14000"))
        listBook.add(Book(R.drawable.book2, "Book2", "11000"))
        listBook.add(Book(R.drawable.book3, "Book3", "10000"))
        listBook.add(Book(R.drawable.book4, "Book4", "9000"))
        listBook.add(Book(R.drawable.book1, "Book1", "14000"))
        listBook.add(Book(R.drawable.book2, "Book2", "11000"))
        listBook.add(Book(R.drawable.book3, "Book3", "10000"))
        listBook.add(Book(R.drawable.book4, "Book4", "9000"))
        listCategory.add(Category("PKN", listBook))

        val listBook2 = ArrayList<Book>()
        listBook2.add(Book(R.drawable.book4, "Book4", "9000"))
        listBook2.add(Book(R.drawable.book1, "Book1", "14000"))
        listBook2.add(Book(R.drawable.book2, "Book2", "11000"))
        listBook2.add(Book(R.drawable.book3, "Book3", "10000"))
        listBook2.add(Book(R.drawable.book1, "Book1", "14000"))
        listBook2.add(Book(R.drawable.book2, "Book2", "11000"))
        listBook2.add(Book(R.drawable.book3, "Book3", "10000"))
        listBook2.add(Book(R.drawable.book4, "Book4", "9000"))
        listCategory.add(Category("Matematika", listBook2))

        val listBook3 = ArrayList<Book>()
        listBook3.add(Book(R.drawable.book3, "Book4", "9000"))
        listBook3.add(Book(R.drawable.book1, "Book1", "14000"))
        listBook3.add(Book(R.drawable.book2, "Book2", "11000"))
        listBook3.add(Book(R.drawable.book4, "Book3", "10000"))
        listBook3.add(Book(R.drawable.book1, "Book1", "14000"))
        listBook3.add(Book(R.drawable.book2, "Book2", "11000"))
        listBook3.add(Book(R.drawable.book3, "Book3", "10000"))
        listBook3.add(Book(R.drawable.book4, "Book4", "9000"))
        listCategory.add(Category("Biologi", listBook3))

        return listCategory
    }
}