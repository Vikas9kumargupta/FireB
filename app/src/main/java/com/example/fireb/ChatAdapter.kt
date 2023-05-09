package com.example.fireb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

@Suppress("DEPRECATION")
class ChatAdapter (private val chatList: ArrayList<ChatEmergency>) : RecyclerView.Adapter<ChatAdapter.MyViewHolder>(){

    private lateinit var myChatListener: ChatAdapter.onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position : Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.chat_item,parent,false)
        return ChatAdapter.MyViewHolder(itemView, myChatListener)
    }

    override fun onBindViewHolder(holder: ChatAdapter.MyViewHolder, position: Int) {
        val currentItem = chatList[position]
        holder.chatImage.setImageResource(currentItem.chatImage)
        holder.chatMsg.text = currentItem.chatMsg
        holder.contactChat.text = currentItem.contactChat
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    fun setOnItemCLickListener(listener: onItemClickListener) {
        myChatListener = listener
    }


    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val chatImage : ShapeableImageView = itemView.findViewById(R.id.chatImage)
        val chatMsg : TextView = itemView.findViewById(R.id.chatMsg)
        val contactChat : TextView = itemView.findViewById(R.id.contactChat)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
}