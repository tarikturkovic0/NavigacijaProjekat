package com.example.navigacijaprojekat.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigacijaprojekat.CitiesListFragmentDirections

import com.example.navigacijaprojekat.R
import com.example.navigacijaprojekat.model.City

class CityAdapter(private val context: Context, private val dataset: List<City>)
    : RecyclerView.Adapter<CityAdapter.CityViewHolder>()
{
    class CityViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val cityNameText: TextView = view.findViewById(R.id.city_id)
        val cityLatText :TextView = view.findViewById(R.id.city_lat)
        val cityLonText :TextView = view.findViewById(R.id.city_lon)
        val mapsButton : Button = view.findViewById(R.id.mapsButton)
        val detailsButton : Button = view.findViewById(R.id.detailsButton)
        public fun detailsSetup(country : String,latituda : String,longituda : String,naziv : String) {
            val action = CitiesListFragmentDirections.actionCitiesListFragment2ToCityDetailsFragment2(country,latituda,longituda,naziv)

            detailsButton.setOnClickListener {

                view.findNavController().navigate(action)
            }
        }
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

       holder.mapsButton.setOnClickListener {
           val intent : Intent = Intent(Intent.ACTION_VIEW)
           intent.setData(Uri.parse("geo:${city.latitude},${city.longitude}"))
           val chooser : Intent = Intent.createChooser(intent,"Launch maps")
           context.startActivity(chooser)
       }
        holder.detailsSetup(city.country,city.latitude.toString(),city.longitude.toString(),city.name)

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}