package com.example.navigacijaprojekat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs


class CityDetailsFragment : Fragment(){



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         //Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_city_details, container, false)
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
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
        val locirajBtn : Button = view.findViewById(R.id.locirajBtn)


        locirajBtn.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtra("city",args.naziv)
                putExtra("latitude", args.latituda)
                putExtra("longitude", args.longituda)
         }
            startActivity(intent)
        }

    }
}
