package com.example.projectfakerapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.example.projectfakerapi.Model.fake
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val response: Call<fake> = NewInstnace.instance.getData("640")
        response.enqueue(object : Callback<fake> {
            override fun onFailure(call: Call<fake>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<fake>, response: Response<fake>) {

                var dataItem= findViewById<TextView>(R.id.text)
                dataItem.text = response.body()!!.data.toString()
                Log.e("Response -", response.body()!!.data.toString())
                Log.e("Response -", response.body()!!.total.toString())
            }
        })
    }

}


