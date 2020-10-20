package com.example.testtabapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testtabapp.Activity.SecondActivity
import com.example.testtabapp.R

class RecyclerViewAdapter(context: Context, users: ArrayList<String>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder?>(){
    private var allUsers = ArrayList<String>()
    private val mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.username_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.name.text = allUsers[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(mContext,holder.name.text, Toast.LENGTH_SHORT).show()
            val intent = Intent(
                mContext,
                SecondActivity::class.java
            )
            intent.putExtra("KEY", holder.name.text)
            mContext.startActivity(intent)

        }
        holder.menuView.setOnClickListener {
            val context = mContext
            val onMenuItemClick: OnMenuItemClick
            onMenuItemClick = context as OnMenuItemClick
            onMenuItemClick.onMenuClick(position)
        }
    }

    override fun getItemCount(): Int = allUsers.size

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var menuView : View = itemView.findViewById(R.id.menu)
    }

    init {
        allUsers = users
        mContext = context
    }

    interface OnMenuItemClick {
        fun onMenuClick(pos: Int)
    }

    fun getUserAtPosition(position: Int): String {
        return allUsers[position]
    }
}