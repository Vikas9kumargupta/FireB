package com.example.fireb

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class NewsAdapter(private val newsList: ArrayList<News>) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private  lateinit var myListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClicking(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        myListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return MyViewHolder(itemView,myListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.hTitle.text = currentItem.heading
        holder.hImage.setImageResource(currentItem.titleImage)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class MyViewHolder(itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        //it holds the view so views are not created everytime, so memory can be saved
        val hTitle: TextView = itemView.findViewById<TextView>(R.id.tvHeading)
        val hImage: ShapeableImageView = itemView.findViewById<ShapeableImageView>(R.id.title_Image)

        init{
            itemView.setOnClickListener {
                listener.onItemClicking(adapterPosition)
            }
        }
    }
}