package com.example.task3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView ? = null
    private var gridLayoutManager:GridLayoutManager ? = null
    private var arrayList: ArrayList<Int> ? = null
    private var adapters: Adapters? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.my_recycler_view)
        gridLayoutManager = GridLayoutManager(applicationContext, 3, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        arrayList = ArrayList()
        arrayList = setDataInList()
        adapters = Adapters(applicationContext, arrayList!!)
        recyclerView?.adapter = adapters
    }

    private fun setDataInList(): ArrayList<Int>{
        var item: ArrayList<Int> = ArrayList()
        for(i in 1..40){
            item.add(i)
        }
        return item
    }
}