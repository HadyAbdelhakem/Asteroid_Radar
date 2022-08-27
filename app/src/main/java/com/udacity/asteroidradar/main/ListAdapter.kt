package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import android.widget.TextView
import com.udacity.asteroidradar.R

class ListAdapter(private val onClickListener: OnClickListener): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var asteroidList = emptyList<Asteroid>()

    class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val textViewId: TextView = itemView.findViewById(R.id.item_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.asteroid_item , parent , false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = asteroidList[position]
        holder.textViewId.text = currentItem.codename
        holder.itemView.setOnClickListener {
            onClickListener.onClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return asteroidList.size
    }

    fun setData(asteroid: List<Asteroid>){
        this.asteroidList = asteroid
        notifyDataSetChanged()
    }

    class OnClickListener(val clickListener: (asteroid:Asteroid) -> Unit){
        fun onClick(asteroid: Asteroid) = clickListener(asteroid)
    }

}