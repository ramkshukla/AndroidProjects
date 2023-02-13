package com.example.task3

import android.animation.*
import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapters(var context: Context, var arrayList: ArrayList<Int>) :
    RecyclerView.Adapter<Adapters.ItemHolder>() {

    lateinit var front_anim: AnimatorSet

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.grid_layout, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.numberTextView.text = arrayList.get(position).toString()
        holder.itemView.setOnClickListener {
            var scale = context.resources.displayMetrics.density
            holder.imageTwo.cameraDistance = 8000 * scale
            front_anim = AnimatorInflater.loadAnimator(
                context,
                R.animator.front_animator
            ) as AnimatorSet
            front_anim.setTarget(holder.imageTwo);
            front_anim.start()
            val handler = Handler()
            handler.postDelayed(Runnable {
                arrayList.removeAt(holder.adapterPosition)
                notifyItemRemoved(holder.adapterPosition)
                notifyItemRangeChanged(holder.adapterPosition, arrayList.size)
            }, 1000)
        }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageTwo: ImageView = itemView.findViewById(R.id.backView)
        var numberTextView: TextView = itemView.findViewById(R.id.numberTextView)
    }

}