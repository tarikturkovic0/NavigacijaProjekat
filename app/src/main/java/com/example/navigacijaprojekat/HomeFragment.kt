package com.example.navigacijaprojekat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.navigacijaprojekat.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,R.layout.fragment_home,container,false)
        binding.showCitiesBtn.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_homeFragment_to_citiesListFragment2)
        }
        binding.addCityBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_addCity2)
        }
        return binding.root
    }


}