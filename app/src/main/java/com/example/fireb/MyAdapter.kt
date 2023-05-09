package com.example.fireb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView


class MyAdapter (private val contactList : ArrayList<ContactList>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position : Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.each_item,parent,false)
        return MyViewHolder(itemView,mListener)
    }

    fun setOnItemCLickListener(listener: onItemClickListener){
        mListener = listener
    }
    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val currentItem = contactList[position]
        holder.contactImage.setImageResource(currentItem.contactImage)
        holder.contactName.text = currentItem.contactName
        holder.contactNumber.text = currentItem.contactNumber

    }
    override fun getItemCount(): Int {
        return contactList.size
    }
    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val contactImage : ShapeableImageView = itemView.findViewById(R.id.contactImage)
        val contactName : TextView = itemView.findViewById(R.id.contactName)
        val contactNumber : TextView = itemView.findViewById(R.id.contactNumber)
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
}

