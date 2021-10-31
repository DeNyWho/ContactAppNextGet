package com.example.contactappnextget.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactappnextget.R

class AddPagerAdapter(var context: FragmentManager):RecyclerView.Adapter<AddPagerAdapter.MyViewHolder>() {
    lateinit var list: List<Int>
    fun setContentList(list: List<Int>) {
        this.list = list
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image = itemView.findViewById<ImageView>(R.id.imageViewMain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPagerAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_model, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddPagerAdapter.MyViewHolder, position: Int) {
        holder.image.setImageResource(list[position])
    }

    override fun getItemCount(): Int = list.size
}