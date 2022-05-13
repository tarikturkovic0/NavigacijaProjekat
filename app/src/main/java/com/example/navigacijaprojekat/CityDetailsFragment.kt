package com.example.navigacijaprojekat

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback


class CityDetailsFragment : Fragment(), OnMapReadyCallback{
    private var mMapView: MapView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         //Inflate the layout for this fragment
        mMapView = view?.findViewById(R.id.mapView)

        initGoogleMap(savedInstanceState);
        return inflater.inflate(R.layout.fragment_city_details, container, false)
    }

    private fun initGoogleMap(savedInstanceState: Bundle?) {
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle("AIzaSyAFE0EFR841EGUh_RSl0lqGzRpyMzQrnBo")
        }

        mMapView?.onCreate(mapViewBundle)
        mMapView?.getMapAsync(this)

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle("AIzaSyAFE0EFR841EGUh_RSl0lqGzRpyMzQrnBo")
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle("AIzaSyAFE0EFR841EGUh_RSl0lqGzRpyMzQrnBo", mapViewBundle)
        }
        mMapView?.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        super.onResume()
        mMapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        mMapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView?.onStop()
    }

    override fun onMapReady(map: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
    }

    override fun onPause() {
        mMapView?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mMapView?.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView?.onLowMemory()
    }
    val args: CityDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tv1: TextView = view.findViewById(R.id.countryText)
        tv1.text = args.country
        val latituda : TextView = view.findViewById(R.id.latitudaText)
        latituda.text = args.latituda
        val longituda : TextView = view.findViewById(R.id.longitudaText)
        longituda.text = args.longituda
        val naziv : TextView = view.findViewById(R.id.nazivGrada)
        naziv.text = args.naziv
        val podijeliBtn : Button = view.findViewById(R.id.shareBtn)
        podijeliBtn.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "${args.naziv} se nalazi na koordinatama ${args.latituda},${args.longituda}")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

    }
}
