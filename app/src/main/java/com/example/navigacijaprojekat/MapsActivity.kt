package com.example.navigacijaprojekat

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.navigacijaprojekat.databinding.ActivityMapsBinding
import com.example.navigacijaprojekat.model.City
import com.example.navigacijaprojekat.model.data.DataSource
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var cityData : List<City>
    private lateinit var distanceMatrix : Array<Array<Double>>

    private fun distance(lat1 : Double,lon1 : Double,lat2 : Double,lon2 : Double) : Double {

        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0.0
        }
        else {
            val theta = lon1 - lon2
            var dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(
                Math.toRadians(lat1)
            ) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta))
            dist = Math.acos(dist)
            dist = Math.toDegrees(dist)
            dist *= 60 * 1.1515
            return dist * 1.609344 // vraca distancu izmedju koordinata u kilometrima
        }
    }
    private fun createDistanceMatrix() {
        val citiesPreferences = this.getSharedPreferences("Cities", Context.MODE_PRIVATE)
        val citiesString  = citiesPreferences.getString("cities",null)
        cityData = DataSource(citiesString).loadCityData()
        distanceMatrix = Array(cityData.size) {Array<Double>(cityData.size) {0.0} }

        for (i in cityData.indices) {
            for (j in cityData.indices) {
                distanceMatrix[i][j] = distance(cityData[i].latitude,cityData[i].longitude,
                    cityData[j].latitude,cityData[j].longitude)
            }
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createDistanceMatrix()

        /*TEST for (strings in distanceMatrix) {
            for (string in strings) {
               Log.d("distanca",string.toString())
            }
        }*/


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val latitude = intent.getStringExtra("latitude")?.toDouble()
        val longitude = intent.getStringExtra("longitude")?.toDouble()
        val cityName = intent.getStringExtra("city")
        val city = LatLng(latitude!!, longitude!!)

        mMap.addMarker(MarkerOptions().position(city).title("Marker in ${cityName}"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(city))
    }
    // 1.Naci  distancu od svakog grada do svakog drugog grada - to ce biti 2d matrica -- GOTOVO
    // 2. Naci sve moguce permutacije iz liste gradova.
    // 3. Za svaku permutaciju izracunati ukupnu distancu (koristeci matricu znat cu koji je grad gdje...)
    // 4. Vratit najmanju distancu


    // BELAJ JE STO IMAMO 100+ GRADOVA A OVAJ ALGORITAM CE DA POGINE NA PREKO DESETAK GRADOVA KOLIKO SAM UPRATIO


}