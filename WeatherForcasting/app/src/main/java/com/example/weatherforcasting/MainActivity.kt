package com.example.weatherforcasting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
lateinit var adapter:locationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getlocations()
    }

private fun getlocations(){
val location: Call<locationDetails> = WeatherService.weatherInstance.getCurrentWeather("London", "no")
    location.enqueue(object: Callback<locationDetails>{
        override fun onResponse(call: Call<locationDetails>, response: Response<locationDetails>) {
            val loc = response.body()
            val locList = findViewById<View>(R.id.locationList) as RecyclerView
            adapter = locationAdapter(this@MainActivity, loc)
            locList.adapter = adapter
            locList.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        override fun onFailure(call: Call<locationDetails>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })
}
}