package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt_name = findViewById<Button>(R.id.btn_start)
        val et_name = findViewById<EditText>(R.id.et_name)
        bt_name.setOnClickListener{
            if (et_name.text.toString().isEmpty()){
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(constants.USER_NAME, et_name.text.toString())
                startActivity(intent)
                finish()
            }
        }

    }
}