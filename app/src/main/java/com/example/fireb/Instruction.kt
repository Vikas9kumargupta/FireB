package com.example.fireb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Instruction : AppCompatActivity() {

    private lateinit var disasterRecyclerView : RecyclerView
    private lateinit var disasterArrayList : ArrayList<Disaster>
    lateinit var disImageId : Array<Int>
    lateinit var disHeading : Array<String>
    lateinit var disDetail : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)

        supportActionBar?.hide()

        disImageId = arrayOf(
            R.drawable.earthquake,
            R.drawable.flood,
            R.drawable.tornado,
            R.drawable.tsunami,
            R.drawable.drought,
            R.drawable.landslides,
            R.drawable.tropicalcyclones,
            R.drawable.volcanic,
            R.drawable.fire,
            R.drawable.thunderstorms,
            R.drawable.avalanche,
            R.drawable.covid
        )
        disHeading = arrayOf(
            "EARTHQUAKE",
            "FLOOD",
            "TORNADO",
            "TSUNAMI",
            "DROUGHT",
            "LANDSLIDES",
            "TROPICAL CYCLONES",
            "VOLCANIC ERUPTION",
            "FIRE",
            "THUNDERSTORM",
            "AVALANCHE",
            "COVID"
        )
        disDetail = arrayOf(
            getString(R.string.disasterDetail1),
            getString(R.string.disasterDetail2),
            getString(R.string.disasterDetail3),
            getString(R.string.disasterDetail4),
            getString(R.string.disasterDetail5),
            getString(R.string.disasterDetail6),
            getString(R.string.disasterDetail7),
            getString(R.string.disasterDetail8),
            getString(R.string.disasterDetail9),
            getString(R.string.disasterDetail10),
            getString(R.string.disasterDetail11),
            getString(R.string.disasterDetail12),

        )
        disasterRecyclerView = findViewById(R.id.recyclerViewDisaster)
        disasterRecyclerView.layoutManager = LinearLayoutManager(this)
        disasterRecyclerView.setHasFixedSize(true)

        disasterArrayList = arrayListOf<Disaster>()
        getUserdata()


    }

    private fun getUserdata() {
        for(i in disImageId.indices){
            val adapter = Disaster(disImageId[i],disHeading[i])
            disasterArrayList .add(adapter)
        }
        val adapter = DisasterAdapter(disasterArrayList)
        disasterRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : DisasterAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                //Toast.makeText(this@Instruction,"You clicked on item no ${position+1}",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@Instruction, DisasterDetail::class.java)
                startActivity(intent)
                intent.putExtra("disHeading", disasterArrayList[position].disasterHeading)
                intent.putExtra("disImageId",disasterArrayList[position].disasterImage)
                intent.putExtra("disDetail",disDetail[position])
                startActivity(intent)
            }
        })
    }

}