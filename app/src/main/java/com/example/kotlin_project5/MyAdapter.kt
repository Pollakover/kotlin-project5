package com.example.kotlin_project5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_project5.retrofit.model.Quotes
import kotlin.collections.List

class MyAdapter(private var data: List<Quotes>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val author_name: TextView = view.findViewById(R.id.author_name)
        val quote: TextView = view.findViewById(R.id.quote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.quote.text = item.quote
        holder.author_name.text = item.author
    }

    override fun getItemCount() = data.size

    fun updateData(newData: List<Quotes>) {
        data = newData
        notifyDataSetChanged()
    }
}
