package com.example.fireb

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fireb.databinding.ActivityEmergencyCallBinding

class Emergency_Call : AppCompatActivity() {

    private lateinit var binding : ActivityEmergencyCallBinding
    private lateinit var contactRecyclerView : RecyclerView
    private lateinit var contactArrayList: ArrayList<ContactList>
    lateinit var imageId: Array<Int>
    lateinit var contactN : Array<String>
    lateinit var contactNum : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmergencyCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageId = arrayOf(
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
        contactN = arrayOf("SankatMochak","Police Helpline","Fire Department","Ambulance","NDRF","Natural Calamities",
            "Gas Leakage","Anti Poison","National Emergency")
        contactNum = arrayOf("00000","100","101","102","011-24363260","1070","1906","1066","1070")

        contactRecyclerView = findViewById(R.id.recyclerView)
        contactRecyclerView.layoutManager = LinearLayoutManager(this)
        contactRecyclerView.setHasFixedSize(true)

        contactArrayList = arrayListOf<ContactList>()
        getUserdata()
    }

    private fun getUserdata() {

        for(i in imageId.indices){
            val contact = ContactList(contactN[i], imageId[i], contactNum[i])
            contactArrayList.add(contact)
        }
        var adapter = MyAdapter(contactArrayList)
        contactRecyclerView.adapter = adapter
        adapter.setOnItemCLickListener(object: MyAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val contact = contactArrayList[position]
                val phNo = contact.contactNumber
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:$phNo"))
                startActivity(intent)
            }

        })
    }
}