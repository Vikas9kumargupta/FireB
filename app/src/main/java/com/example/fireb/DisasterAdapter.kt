package com.example.fireb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class DisasterAdapter(private val disasterList : ArrayList<Disaster>) : RecyclerView.Adapter<DisasterAdapter.MyViewHolder>(){

    private lateinit var myDisasterListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        myDisasterListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.disaster_item,parent,false)
        return MyViewHolder(itemView,myDisasterListener)
    }

    override fun getItemCount(): Int {
        return disasterList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = disasterList[position]
        holder.titleDisasterImage.setImageResource(currentItem.disasterImage)
        holder.tvDisasterHeading.text = currentItem.disasterHeading
    }
    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val titleDisasterImage : ShapeableImageView = itemView.findViewById<ShapeableImageView>(R.id.disasterImage)
        val tvDisasterHeading : TextView = itemView.findViewById<TextView>(R.id.disasterHeading)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}