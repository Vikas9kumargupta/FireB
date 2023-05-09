package com.example.fireb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsUpdate : AppCompatActivity() {

    private lateinit var newsRecyclerView : RecyclerView
    private lateinit var newsArrayList : ArrayList<News>
    lateinit var imageId : Array<Int>
    lateinit var heading : Array<String>
    lateinit var newsDetail : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_update)

        supportActionBar?.hide()
        newsRecyclerView = findViewById(R.id.rView)

        imageId = arrayOf(
            R.drawable.img,
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_4,
            R.drawable.img_5,
            R.drawable.img_6,
            R.drawable.img_7
        )
        heading = arrayOf(
            "East of the Bay? Mocha may change course towards Myanmar, according to predictions",
            "Climate change worsened extreme weather events in 2022: State of the Global Climate report",
            "Odisha experiencing the country’s worst forest fires again, 642 incidents in March",
            "White Death: Avalanche strikes near Sikkim’s Nathula Road for the first time; seven tourists killed, 13 rescued",
            "H3N2 influenza virus symptoms include fever and cough; doctors prescribe best time to take annual flue vaccine",
            "Three year since outbreak of COVID-19 pandemic in india, country records 66 new infections",
            "Shaken by Giaspura gas leak tragedy, Ludhiana school hold disaster mgmt drill",
            "Rwanda floods kill 130, destroy over 5,000 houses"
        )


        newsDetail = arrayOf(
            getString(R.string.newsDetail0),getString(R.string.newsDetail1),getString(R.string.newsDetail2),
            getString(R.string.newsDetail3),getString(R.string.newsDetail4),getString(R.string.newsDetail5),
            getString(R.string.newsDetail6),getString(R.string.newsDetail7)
        )

        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.setHasFixedSize(true)

        newsArrayList = arrayListOf<News>()
        getUserdata()
    }

    private fun getUserdata() {
        for(i in imageId.indices){
            val news = News(imageId[i],heading[i])
            newsArrayList.add(news)
        }
        val adapter = NewsAdapter(newsArrayList)
        newsRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object: NewsAdapter.onItemClickListener{
            override fun onItemClicking(position: Int) {
//                Toast.makeText(this@NewsUpdate,"You clicked on item no. $position", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@NewsUpdate,ActivityNewsDetails::class.java)
                intent.putExtra("heading",newsArrayList[position].heading)
                intent.putExtra("imageId",newsArrayList[position].titleImage)
                intent.putExtra("newsDetail",newsDetail[position])
                startActivity(intent)
            }

        })
    }
}