package com.example.navigacijaprojekat

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigacijaprojekat.databinding.FragmentAddCityBinding

class AddCity : Fragment() {
    private var _binding: FragmentAddCityBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddCityBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* // OVO CU DOVRSIT,NE RADI.
        val preferences = view.context.getSharedPreferences("Cities", Context.MODE_PRIVATE)

        val citiesString  = preferences.getString("cities",null)
        val insertPosition = citiesString!!.indexOf(']')
        binding.submitButton.setOnClickListener { view : View ->
            val updatedCities = StringBuilder(citiesString).insert(insertPosition!!,
                """{name:"${binding.nazivGrada.text.toString()}","latitude":"${binding.latituda.text.toString()}",
                    "longitude":"${binding.longituda.text}","country":"${binding.drzava.text}" "}]""".trimMargin()).toString()
        val editor : SharedPreferences.Editor  = preferences.edit().putString("cities",
                updatedCities)
            editor.apply()
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}