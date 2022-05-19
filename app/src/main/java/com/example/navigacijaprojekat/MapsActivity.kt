package com.example.navigacijaprojekat

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.navigacijaprojekat.databinding.ActivityMapsBinding
import com.example.navigacijaprojekat.model.City
import com.example.navigacijaprojekat.model.data.DataSource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*
import kotlin.properties.Delegates

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    //korisnikove trenutne koordinate
    private var myLatitude : Double = 0.0
    private var myLongitude : Double = 0.0


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
    private fun permutationTotalDistance(list : List<City>) : Double {
        var total_distance = 0.0
        val cityName = intent.getStringExtra("city") // ovo vrv stavit kao atribut ksnije jer se koristi na 3 mjesta

        // posto se pocetni grad ne nalazi u permutacijama(jer je uvijek prvi) racuna se distanca od pocetnog do prvog iz liste
        val startingCityIndex = cityData.indexOf(cityData.find{it.name.compareTo(cityName!!) == 0})
        val firstCityIndex =  cityData.indexOf(cityData.find{it.name.compareTo(list[0].name) == 0})
        total_distance += distanceMatrix[startingCityIndex][firstCityIndex]
        for (i in list.indices) { // onda se racuna distanca za ostale...

            if (i+1 < list.size) {
                val firstCityIndex = cityData.indexOf(cityData.find{it.name.compareTo(list[i].name) == 0}) // nalazi indeks prvog grada
                val secondCityIndex =  cityData.indexOf(cityData.find{it.name.compareTo(list[i+1].name) == 0})
                val distance : Double = distanceMatrix[firstCityIndex][secondCityIndex]
                total_distance += distance
            }
        }
        return total_distance
    }
    private fun permute(CityList: List<City>): List<List<City>> {
        val result = LinkedList<LinkedList<City>>()
        result += LinkedList<City>()

        for(n in CityList){
            insert(result, n)
        }

        return result
    }

    private fun insert(result: LinkedList<LinkedList<City>>, n: City){
        repeat(result.size){
            val list = result.removeFirst()
            // insert n to every position in list.
            for(i in 0..list.lastIndex+1){
                val new = LinkedList(list)
                new.add(i,n)
                result += (new)
            }
        }
    }
    private fun shortestPath(permutations : List<List<City>>) : List<City> {
        var distance = permutationTotalDistance(permutations[0]);
        val cityName = intent.getStringExtra("city")
        var result : List<City>
        result = permutations[0]
        for (permutation in permutations) {
            //Log.d("staba",permutation.toString())
            val permutationDistance = permutationTotalDistance(permutation)
            if (permutationDistance < distance) {
                distance = permutationDistance
                result = permutation
                var pathString = cityName + "-> "
                for (city in result) {
                    pathString += city.name + "-> "
                }
                binding.putanja.text = pathString
                binding.distanca.text = "Ukupan put: "+  distance.toString() + " km"
                Log.d("distanca",""+distance.toString())
                Log.d("rezz",result.toString())

            }
        }
        return result
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    @SuppressLint("MissingPermission")
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.podijeli -> {

                fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                    myLatitude = location!!.latitude
                    myLongitude = location.longitude
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "Ja se nalazim na koordinatama: $myLatitude, $myLongitude ")
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
            }
            true
        }
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> {
            true
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createDistanceMatrix()
        setSupportActionBar(findViewById(R.id.toolbar))
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

        // grad u kojem se nalazimo je uvijek prvi u permutacijama,pa se izbacuje iz liste
        val citiesToPermute = cityData.toMutableList().filter { it.name.compareTo(cityName!!) != 0  }

        val cityPermutations = permute(citiesToPermute)
        shortestPath(cityPermutations)

    }
    // 1.Naci  distancu od svakog grada do svakog drugog grada - to ce biti 2d matrica -- GOTOVO
    // 2. Naci sve moguce permutacije iz liste gradova.
    // 3. Za svaku permutaciju izracunati ukupnu distancu
// +(koristeci matricu znat cu koji je grad gdje...)
    // 4. Vratit najmanju distancu


    // BELAJ JE STO IMAMO 100+ GRADOVA
// A OVAJ ALGORITAM CE DA POGINE NA PREKO DESETAK GRADOVA KOLIKO SAM UPRATIO


}