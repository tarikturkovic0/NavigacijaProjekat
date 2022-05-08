package com.example.navigacijaprojekat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.navigacijaprojekat.R
import com.example.navigacijaprojekat.model.City

class CityAdapter(private val context: Context, private val dataset: List<City>)
    : RecyclerView.Adapter<CityAdapter.CityViewHolder>()
{
    class CityViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val cityNameText: TextView = view.findViewById(R.id.city_id)
        val cityLatText :TextView = view.findViewById(R.id.city_lat)
        val cityLonText :TextView = view.findViewById(R.id.city_lon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.city_view, parent, false)
        return CityViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = dataset[position]
        holder.cityNameText.text = city.name
        holder.cityLatText.text = city.latitude.toString()
        holder.cityLonText.text = city.longitude.toString()

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}