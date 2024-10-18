package com.example.kotlin_project5

import android.net.http.QuicOptions
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.kotlin_project5.retrofit.api.MainApi
import com.example.kotlin_project5.retrofit.model.AppDatabase
import com.example.kotlin_project5.retrofit.model.Quotes
import com.example.kotlin_project5.retrofit.model.QuotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "quotes.db").build()
        val quoteRep = QuotesRepository(database.quoteDao())

        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com").addConverterFactory(
                GsonConverterFactory.create()).build()
        val quoteApi = retrofit.create(MainApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val quotes = mutableListOf<Quotes>()
            for (i in 1..30) {
                val quote = quoteApi.getQuoteById(i)
                quotes.add(Quotes(quote.id, quote.quote, quote.author))
            }

            quoteRep.insert(quotes)

            val storedQuotes = quoteRep.allQuotes
            runOnUiThread(){
                storedQuotes.observe(this@ListActivity, Observer { data ->
                    val adapter = MyAdapter(data)
                    recyclerView.adapter = adapter
                })
            }

        }

        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}