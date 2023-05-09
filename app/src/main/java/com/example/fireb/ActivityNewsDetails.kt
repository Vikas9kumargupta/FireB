package com.example.fireb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ActivityNewsDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        supportActionBar?.hide()

        val headingNews : TextView = findViewById<TextView>(R.id.newsHeading)
        val imageNews : ImageView = findViewById<ImageView>(R.id.newsImage)
        val mainNews : TextView = findViewById<TextView>(R.id.newsContent)

        val bundle : Bundle? = intent.extras
        val heading = bundle!!.getString("heading")
        val imageId = bundle.getInt("imageId")
        val newsContent = bundle.getString("newsDetail")

        headingNews.text = heading
        imageNews.setImageResource(imageId)
        mainNews.text = newsContent
    }
}