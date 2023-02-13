package com.example.animationapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clockWise(view:View){
        var image:ImageView  = findViewById(R.id.imageView)
        var animation:Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.clockwise)
        image.startAnimation(animation)
    }

    fun zoom(view:View){
        var image:ImageView = findViewById(R.id.imageView)
        var animation1:Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom)
        image.startAnimation(animation1)
    }

    fun fade(view:View){
        var image:ImageView = findViewById(R.id.imageView)
        var animation1:Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade)
        image.startAnimation(animation1)
    }

    fun blink(view:View){
        var image:ImageView = findViewById(R.id.imageView)
        var animation1:Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.blink)
        image.startAnimation(animation1)
    }

    fun move(view:View){
        var image:ImageView = findViewById(R.id.imageView)
        var animation1:Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.move)
        image.startAnimation(animation1)
    }
    fun slide(view:View){
        var image:ImageView = findViewById(R.id.imageView)
        var animation1:Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slide)
        image.startAnimation(animation1)
    }
}