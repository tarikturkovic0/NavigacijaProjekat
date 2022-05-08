package com.example.navigacijaprojekat.model.data
import com.example.navigacijaprojekat.R
import com.example.navigacijaprojekat.model.City

class DataSource {
    fun loadCityData() : List<City> {
        return listOf<City>(
            City(R.string.showLocations) ,
                    City(R.string.showLocations),
                    City(R.string.showLocations),
                    City(R.string.showLocations),

        )
    }
}