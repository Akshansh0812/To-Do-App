package com.example.todoapp

import android.content.Intent
import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class Adapter(private var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolderClass>() {
    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.findViewById<TextView>(R.id.title)
        val priority = itemView.findViewById<TextView>(R.id.priority)
        val layout = itemView.findViewById<LinearLayout>(R.id.mylayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        when (data[position].priority.lowercase(Locale.ROOT)) {

            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#EDC988"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#00917C"))
        }
        holder.title.text = data[position].title
        holder.priority.text = data[position].priority
        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,UpdateCard::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}