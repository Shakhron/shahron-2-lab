package com.example.shahron2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapRecycle: RecyclerView.Adapter<AdapRecycle.MyHolder>() {

    var array = listOf<Temp>()
        set(value) {
            field = value
        }

    class MyHolder(view: View): RecyclerView.ViewHolder(view) {
        val city: TextView = view.findViewById(R.id.cityTv)
        val temp: TextView = view.findViewById(R.id.tempTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_item, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = array[position]
        holder.city.text = item.city
        holder.temp.text = item.temper

    }

    override fun getItemCount(): Int {
        return array.size
    }

}