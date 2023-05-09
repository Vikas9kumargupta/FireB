package com.example.fireb

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fireb.databinding.ActivityChatBinding

class Chat : AppCompatActivity() {

    private lateinit var binding : ActivityChatBinding
    private lateinit var chatRecyclerView : RecyclerView
    private lateinit var chatArrayList: ArrayList<ChatEmergency>
    private lateinit var imageChat: Array<Int>
    private lateinit var chatName : Array<String>
    private lateinit var contactChat : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageChat = arrayOf(
            R.drawable.sankatmochak_logo,
            R.drawable.police,
            R.drawable.fire,
            R.drawable.ambulance,
            R.drawable.ndrf,
            R.drawable.natural_calamities,
            R.drawable.gas_leakage,
            R.drawable.anti_poison,
            R.drawable.national_emergency
        )
        chatName = arrayOf("SankatMochak","Police Helpline","Fire Department","Ambulance","NDRF","Natural Calamities",
            "Gas Leakage","Anti Poison","National Emergency")

        contactChat= arrayOf("+916394031829","100","101","102","011-24363260","1070","1906","1066","1070")

        chatRecyclerView = findViewById(R.id.recyclerViewChat)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.setHasFixedSize(true)

        chatArrayList = arrayListOf<ChatEmergency>()
        getUserdata()
    }
    private fun getUserdata() {

        for(i in imageChat.indices){
            val contactChat = ChatEmergency(contactChat[i], imageChat[i], chatName[i])
            chatArrayList.add(contactChat)
        }
        val adapter = ChatAdapter(chatArrayList)
        chatRecyclerView.adapter = adapter
        adapter.setOnItemCLickListener(object: ChatAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val contactCh = chatArrayList[position]
                val phNo = contactCh.contactChat
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, contactChat)
                intent.putExtra("address", phNo)
                startActivity(intent)
            }
        })
    }
}