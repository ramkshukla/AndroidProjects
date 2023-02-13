package com.example.weatherforcasting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class locationAdapter(val context: Context, val locations: locationDetails?): RecyclerView.Adapter<locationAdapter.locationViewHolder>() {

    class locationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name =itemView.findViewById<TextView>(R.id.name)
        var region =itemView.findViewById<TextView>(R.id.region)
        var country =itemView.findViewById<TextView>(R.id.country)
        var lat =itemView.findViewById<TextView>(R.id.lat)
        var lon =itemView.findViewById<TextView>(R.id.lon)
        var tz_id =itemView.findViewById<TextView>(R.id.tz_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): locationViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return locationViewHolder(view)
    }

    override fun onBindViewHolder(holder: locationViewHolder, position: Int) {
        val location: locationDetails? = locations
        if (location != null) {
            holder.name.text =location.name
        }
        if (location != null) {
            holder.region.text =location.region
        }
        if (location != null) {
            holder.country.text =location.country
        }
        if (location != null) {
            holder.lat.text =location.lat.toString()
        }
        if (location != null) {
            holder.lon.text =location.lon.toString()
        }
        if (location != null) {
            holder.tz_id.text =location.tz_id
        }
    }

    override fun getItemCount(): Int {
        return 1
    }
}