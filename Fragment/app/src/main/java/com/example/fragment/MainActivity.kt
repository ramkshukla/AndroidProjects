package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flfragment, firstFragment)
            commit()
        }

        val btnFragment1 = findViewById<Button>(R.id.btnFragment1)
        btnFragment1.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flfragment, firstFragment)
                commit()
            }
        }

        val btnFragment2 = findViewById<Button>(R.id.btnFragment2)
        btnFragment2.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flfragment, secondFragment)
                commit()
            }
        }
    }
}