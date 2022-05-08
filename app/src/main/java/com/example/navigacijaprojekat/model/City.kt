package com.example.navigacijaprojekat.model

import com.example.navigacijaprojekat.model.data.DataSource
import kotlinx.serialization.Serializable


@Serializable
data class City(val name : String, val latitude : Double, val longitude : Double) {

}
@Serializable
data class Cities(
    @Serializable(with = DataSource.CityListSerializer::class)
    val cities: List<City>
)
