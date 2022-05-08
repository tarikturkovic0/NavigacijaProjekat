package com.example.navigacijaprojekat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigacijaprojekat.adapter.CityAdapter
import com.example.navigacijaprojekat.databinding.FragmentCitiesListBinding
import com.example.navigacijaprojekat.model.City
import com.example.navigacijaprojekat.model.data.DataSource

class CitiesListFragment : Fragment() {

    lateinit var recyclerView : RecyclerView;
    private var _binding: FragmentCitiesListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentCitiesListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rv;
        val cityDataSet = DataSource().loadCityData()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CityAdapter(view.context,cityDataSet)
        }
    }


}