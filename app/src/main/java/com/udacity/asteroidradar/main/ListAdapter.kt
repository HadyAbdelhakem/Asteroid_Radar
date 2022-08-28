package com.udacity.asteroidradar.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.udacity.asteroidradar.R

class ListAdapter(private val onClickListener: OnClickListener): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var asteroidList = emptyList<Asteroid>()
    private var context: Context? = null

    class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val textViewId: TextView = itemView.findViewById(R.id.item_id)
        val textViewDate: TextView = itemView.findViewById(R.id.date_text)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.asteroid_item , parent , false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = asteroidList[position]
        holder.textViewId.text = currentItem.codename
        holder.textViewDate.text = "("+currentItem.closeApproachDate+")"
        if (currentItem.isHazadous){
            holder.imageView.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.ic_status_potentially_hazardous) })
        }
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