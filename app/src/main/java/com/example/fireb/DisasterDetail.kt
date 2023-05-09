package com.example.fireb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DisasterDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disaster_detail)

        supportActionBar?.hide()

        val disasterHead : TextView = findViewById(R.id.disasterHeading)
        val disasterImage1 : ImageView = findViewById(R.id.disasterImage)
        val disasterContent1 : TextView = findViewById(R.id.disasterContent)

        val bundle : Bundle? = intent.extras
        val disasterName = bundle!!.getString("disHeading")
        val disasterImg = bundle.getInt("disImageId")
        val disasterCont = bundle.getString("disDetail")

        disasterHead.text = disasterName
        disasterImage1.setImageResource(disasterImg)
        disasterContent1.text = disasterCont

    }
}