package com.example.task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {
    private var title: String = ""
    private var description:String = ""
    private var image: String= ""

    lateinit var tvText: TextView
    lateinit var tvDesc: TextView
    lateinit var iv: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        tvText = view.findViewById(R.id.titleId)
        tvDesc = view.findViewById(R.id.descId)
        iv = view.findViewById(R.id.newsImage)
        val bundle = arguments

        if (bundle != null){
            title = bundle.getString("title")!!
            description = bundle.getString("description")!!
            image = bundle.getString("image")!!
        }
        tvText.text = title
        tvDesc.text = description
        context?.let { Glide.with(it).load(image).into(iv) }


        return view
    }


}
