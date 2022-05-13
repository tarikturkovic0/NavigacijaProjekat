package com.example.navigacijaprojekat

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.navigacijaprojekat.databinding.FragmentAddCityBinding
import com.google.android.gms.common.api.internal.LifecycleCallback.getFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


class AddCity : Fragment() {
    private var _binding: FragmentAddCityBinding? = null
    private val binding get() = _binding!!
    lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {

        val drawerLayout : DrawerLayout? = view?.findViewById(R.id.dodaj_grad)
        val navView : NavigationView? = view?.findViewById(R.id.nav_view)


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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(true) {
            requireView().findNavController().navigate(com.example.navigacijaprojekat.R.id.action_homeFragment_to_citiesListFragment2)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferences = view.context.getSharedPreferences("Cities", Context.MODE_PRIVATE)
        val citiesString  = preferences.getString("cities",null)
        val insertPosition = citiesString!!.indexOf(']')
        binding.submitButton.setOnClickListener {
            val updatedCities = StringBuilder(citiesString).insert(insertPosition,
                """,{"name":"${binding.nazivGrada.text.toString()}", "latitude":"${binding.latituda.text.toString()}",
                    "longitude":"${binding.longituda.text}", "country":"${binding.drzava.text}" } """.trimMargin()).toString()
            val editor : SharedPreferences.Editor  = preferences.edit().putString("cities",updatedCities)
            editor.apply()
            binding.nazivGrada.text.clear()
            binding.longituda.text.clear()
            binding.latituda.text.clear()
            binding.drzava.text.clear()
            Snackbar.make(it,"Uspješno ste dodali grad!",
                Snackbar.LENGTH_LONG).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
                .setBackgroundTint(Color.parseColor("#FFF5EE"))
                .setAction("OK"){
                    Toast.makeText(view.context,"",Toast.LENGTH_SHORT).show()}.show()

        }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}