package com.example.sharedpreference

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast

const val name: String = "nameKey"
const val myPreferences = "MyPref"
class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tv = findViewById<TextView>(R.id.tv)
        var btn = findViewById<Button>(R.id.button)
        sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE)
        btn.setOnClickListener(this)
        tv.text = sharedPreferences.getString("nameKey", "default")


    }

    override fun onClick(p0: View?) {
        var editText = findViewById<EditText>(R.id.edit)
        val text: String = editText.getText().toString()
        val editor: SharedPreferences.Editor  = sharedPreferences.edit()
        editor.putString(name, text)
        editor.commit()
        Toast.makeText(this, "Thanks", Toast.LENGTH_SHORT).show()

    }
}