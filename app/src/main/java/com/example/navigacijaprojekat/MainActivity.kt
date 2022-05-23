package com.example.navigacijaprojekat

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.navigacijaprojekat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        val preferences = getSharedPreferences("Cities", Context.MODE_PRIVATE)
        val cities = preferences.getString("cities",null)
        if (cities == null) {
            val editor : SharedPreferences.Editor  = preferences.edit().putString("cities",
                citiesJSON)
            editor.apply()
        }

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

    }


}