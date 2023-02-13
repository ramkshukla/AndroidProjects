package com.example.task2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init

class NewsAdapter(
    val context: Context,
    val articles: List<Articles>,
) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article: Articles = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)

//        holder.newsImage.setOnClickListener(object  : View.OnClickListener {
//            override fun onClick(p0: View?) {
//                myInterface.onItemClick(article.title, article.description)
//            }
//        })
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val bundle = Bundle()
                bundle.putString("title", article.title)
                bundle.putString("description", article.description)
                bundle.putString("image", article.urlToImage)
                val activity = v!!.context as AppCompatActivity
                val detailFragment = DetailFragment()
                detailFragment.arguments = bundle
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fLID, detailFragment).addToBackStack(null).commit()

            }

        })

    }

    override fun getItemCount(): Int {
        return articles.size
    }


    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        var newsTitle = itemView.findViewById<TextView>(R.id.newTitle)
        var newsDescription = itemView.findViewById<TextView>(R.id.newsDescription)


    }


}