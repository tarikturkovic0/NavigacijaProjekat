package com.example.navigacijaprojekat.model.data


import com.example.navigacijaprojekat.model.City
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonTransformingSerializer




class DataSource(val citiesStr : String?) {

    object CityListSerializer : JsonTransformingSerializer<List<City>>(ListSerializer(City.serializer())) {
        // If response is not an array, then it is a single object that should be wrapped into the array

        override fun transformDeserialize(element: JsonElement): JsonElement =
            if (element !is JsonArray) JsonArray(listOf(element)) else element
    }

    fun loadCityData() : List<City> {

        val list = Json.decodeFromString<List<City>>(citiesStr!!)
       return list

    }
}