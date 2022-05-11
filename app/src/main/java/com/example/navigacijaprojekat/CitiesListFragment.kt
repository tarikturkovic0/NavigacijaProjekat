package com.example.navigacijaprojekat


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigacijaprojekat.adapter.CityAdapter
import com.example.navigacijaprojekat.databinding.FragmentCitiesListBinding
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



        val citiesPreferences = view.context.getSharedPreferences("Cities", Context.MODE_PRIVATE)
        val citiesString  = citiesPreferences.getString("cities",null)

        val cityDataSet = DataSource(citiesString).loadCityData()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CityAdapter(view.context,cityDataSet)
        }


    }


}

val citiesJSON = """
            [{
			"name": "Tokyo",
			"latitude": 35.5147457,
			"longitude": 139.4839981,
            "country" : "Japan"
		},
		{
			"name": "Manila",
			"latitude": 14.5906216,
			"longitude": 120.9799696,
            "country" : "Philippines"
		},
		{
			"name": "Jakarta",
			"latitude": -6.1753942,
			"longitude": 106.827183,
            "country" : "Indonesia"
		},
		{
			"name": "Delhi",
			"latitude": 28.6517178,
			"longitude": 77.2219388,
            "country" : "India"
		},
		{
			"name": "Seoul-Incheon",
			"latitude": 37.5056926,
			"longitude": 126.7358775,
             "country" : "South Korea"
		},
		{
			"name": "Mumbai",
			"latitude": 18.9387711,
			"longitude": 72.8353355,
             "country" : "India"
		},
		{
			"name": "Mexico City",
			"latitude": 19.4326296,
			"longitude": -99.1331785,
            "country" : "Mexico"
		},
		{
			"name": "Guangzhou",
			"latitude": 23.1592862,
			"longitude": 113.4566381,
            "country" : "China"
		},
		{
			"name": "New York",
			"latitude": 40.7127281,
			"longitude": -74.0060152,
            "country" : "USA"
		},
		{
			"name": "Dhaka",
			"latitude": 23.7593572,
			"longitude": 90.3788136,
"country" : "Bangladesh"
		},
		{
			"name": "Osaka–Kobe",
			"latitude": 34.8038695,
			"longitude": 135.5156628,
"country" : "Japan"
		},
		{
			"name": "Moscow",
			"latitude": 55.7504461,
			"longitude": 37.6174943,
"country" : "Russia"
		},
		{
			"name": "Cairo",
			"latitude": 30.048819,
			"longitude": 31.243666,
"country" : "Egypt"
		},
		{
			"name": "Beijing",
			"latitude": 39.906217,
			"longitude": 116.3912757,
"country" : "Beijing"
		},
		{
			"name": "Karachi",
			"latitude": 24.8667795,
			"longitude": 67.0311286,
"country" : "Pakistan"
		},
		{
			"name": "Buenos Aires",
			"latitude": -34.6075682,
			"longitude": -58.4370894,
"country" : "Argentina"
		},
		{
			"name": "Los Angeles",
			"latitude": 34.0536909,
			"longitude": -118.2427666,
"country" : "USA"
		},
		{
			"name": "Bangkok",
			"latitude": 13.7542529,
			"longitude": 100.493087,
"country" : "Thailand"
		},
		{
			"name": "São Paulo",
			"latitude": -23.5506507,
			"longitude": -46.6333824,
"country" : "Brazil"
		},
		{
			"name": "Lagos",
			"latitude": 6.4550575,
			"longitude": 3.3941795,
"country" : "Nigeria"
		},
		{
			"name": "Kolkata",
			"latitude": 22.5677459,
			"longitude": 88.3476023,
"country" : "India"
		},
		{
			"name": "Shenzhen",
			"latitude": 22.555454,
			"longitude": 114.0543297,
"country" : "China"
		},
		{
			"name": "Istanbul",
			"latitude": 41.0096334,
			"longitude": 28.9651646,
"country" : "Turkey"
		},
		{
			"name": "Tehran",
			"latitude": 35.7006177,
			"longitude": 51.4013785,
"country" : "Iran"
		},
		{
			"name": "Tianjin",
			"latitude": 39.1235635,
			"longitude": 117.1980785,
"country" : "China"
		},
		{
			"name": "Shanghai",
			"latitude": 31.2252985,
			"longitude": 121.4890497,
"country" : "China"
		},
		{
			"name": "Rio de Janeiro",
			"latitude": -22.9110137,
			"longitude": -43.2093727,
"country" : "Brazil"
		},
		{
			"name": "Lima",
			"latitude": -12.0621065,
			"longitude": -77.0365256,
"country" : "Peru"
		},
		{
			"name": "Lahore",
			"latitude": 31.5656079,
			"longitude": 74.3141775,
"country" : "Pakistan"
		},
		{
			"name": "Ho Chi Minh",
			"latitude": 10.6497452,
			"longitude": 106.7619794,
"country" : "China"
		},
		{
			"name": "London",
			"latitude": 51.5073219,
			"longitude": -0.1276474,
"country" : "UK"
		},
		{
			"name": "Bangalore",
			"latitude": 12.9791198,
			"longitude": 77.5912997,
"country" : "India"
		},
		{
			"name": "Paris",
			"latitude": 48.8566969,
			"longitude": 2.3514616,
"country" : "France"
		},
		{
			"name": "Chengdu",
			"latitude": 30.6624205,
			"longitude": 104.0633219,
"country" : "China"
		},
		{
			"name": "Nagoya (Chūkyō)",
			"latitude": 35.1387199,
			"longitude": 136.9665068,
"country" : "China"
		},
		{
			"name": "Chennai",
			"latitude": 13.0801721,
			"longitude": 80.2838331,
"country" : "China"

		},
		{
			"name": "Hyderabad",
			"latitude": 25.3801017,
			"longitude": 68.3750376,
"country" : "China"

		},
		{
			"name": "Bogotá",
			"latitude": 4.59808,
			"longitude": -74.0760439,
"country" : "Columbia"

		},
		{
			"name": "Taipei-Taoyuan",
			"latitude": 24.9929995,
			"longitude": 121.3010003,
"country" : "China"

		},
		{
			"name": "Kinshasa",
			"latitude": -4.3217055,
			"longitude": 15.3125974,
"country" : "China"

		},
		{
			"name": "Chicago",
			"latitude": 41.8755616,
			"longitude": -87.6244212,
"country" : "USA"

		},
		{
			"name": "Wuhan",
			"latitude": 30.5951051,
			"longitude": 114.2999353,
"country" : "China"

		},
		{
			"name": "Onitsha",
			"latitude": 6.133234,
			"longitude": 6.792318,
"country" : "China"

		},
		{
			"name": "Kuala Lumpur",
			"latitude": 3.1516964,
			"longitude": 101.6942371,
"country" : "Malesia"

		},
		{
			"name": "Dongguan",
			"latitude": 23.0444712,
			"longitude": 113.7465512,
"country" : "Japan"

		},
		{
			"name": "Hanoi",
			"latitude": 21.0294498,
			"longitude": 105.8544441,
"country" : "Vietnam"

		},
		{
			"name": "Ahmedabad",
			"latitude": 23.0216238,
			"longitude": 72.5797068,
"country" : "India"

		},
		{
			"name": "Washington, D.C.",
			"latitude": 39.2908816,
			"longitude": -76.610759,
"country" : "USA"

		},
		{
			"name": "Luanda",
			"latitude": -8.8272699,
			"longitude": 13.2439512,
"country" : "Angola"

		},
		{
			"name": "Baghdad",
			"latitude": 33.3024309,
			"longitude": 44.3787992,
"country" : "Iraq"

		},
		{
			"name": "Xi'an–Xianyang",
			"latitude": 34.3075655,
			"longitude": 108.7234362,
"country" : "China"

		},
		{
			"name": "Hong Kong",
			"latitude": 22.2793278,
			"longitude": 114.1628131,
"country" : "China"

		},
		{
			"name": "Johannesburg",
			"latitude": 35.3732015,
			"longitude": -117.6432874,
"country" : "South Africa"

		},
		{
			"name": "Xingyang",
			"latitude": 34.7891274,
			"longitude": 113.3724566,
"country" : "China"

		},
		{
			"name": "Boston",
			"latitude": 41.7048225,
			"longitude": -71.5483952,
"country" : "USA"

		},
		{
			"name": "Shenyang",
			"latitude": 41.8041094,
			"longitude": 123.4276363,
"country" : "China"

		},
		{
			"name": "Hangzhou",
			"latitude": 30.2489634,
			"longitude": 120.2052342,
"country" : "China"

		},
		{
			"name": "Toronto",
			"latitude": 43.6706177,
			"longitude": -79.3746817,
"country" : "Canada"

		},
		{
			"name": "Quanzhou",
			"latitude": 24.9038801,
			"longitude": 118.5851458,
"country" : "China"

		},
		{
			"name": "Fort Worth",
			"latitude": 32.7379037,
			"longitude": -97.2394281,
"country" : "USA"

		},
		{
			"name": "Santiago",
			"latitude": 9.8694792,
			"longitude": -83.7980749,
"country" : "Chile"

		},
		{
			"name": "Houston",
			"latitude": 29.7589382,
			"longitude": -95.3676974,
"country" : "USA"

		},
		{
			"name": "Surat",
			"latitude": 45.9383,
			"longitude": 3.2553,
"country" : "India"

		},
		{
			"name": "Madrid",
			"latitude": 40.4167047,
			"longitude": -3.7035825,
"country" : "Spain"

		},
		{
			"name": "Nanjing",
			"latitude": 32.0609736,
			"longitude": 118.7916458,
"country" : "China"

		},
		{
			"name": "Pune",
			"latitude": 18.521428,
			"longitude": 73.8544541,
"country" : "India"

		},
		{
			"name": "Riyadh",
			"latitude": 24.6319692,
			"longitude": 46.7150648,
"country" : "Saudi Arabia"

		},
		{
			"name": "Nairobi",
			"latitude": -1.2832533,
			"longitude": 36.8172449,
"country" : "Kenya"

		},
		{
			"name": "Qingdao–Jimo",
			"latitude": 36.4660932,
			"longitude": 120.6190022,
"country" : "China"

		},
		{
			"name": "Miami",
			"latitude": 25.7742658,
			"longitude": -80.1936589,
"country" : "USA"

		},
		{
			"name": "San Jose",
			"latitude": 8.3512768,
			"longitude": -81.0390519,
"country" : "USA"

		},
		{
			"name": "Philadelphia",
			"latitude": 39.9527237,
			"longitude": -75.1635262,
"country" : "USA"

		},
		{
			"name": "Khartoum",
			"latitude": 15.593325,
			"longitude": 32.53565,
"country" : "Sudan"

		},
		{
			"name": "Amman",
			"latitude": 31.9515694,
			"longitude": 35.9239625,
"country" : "Jordan"

		},
		{
			"name": "Atlanta",
			"latitude": 33.7490987,
			"longitude": -84.3901849,
"country" : "Atlanta"

		},
		{
			"name": "Singapore",
			"latitude": 1.340863,
			"longitude": 103.8303918,
"country" : "Singapore"

		},
		{
			"name": "Chongqing",
			"latitude": 29.5585712,
			"longitude": 106.5492822,
"country" : "China"

		},
		{
			"name": "Yangon",
			"latitude": 16.7967129,
			"longitude": 96.1609916,
"country" : "Japan"

		},
		{
			"name": "Suzhou",
			"latitude": 31.3016935,
			"longitude": 120.5810725,
"country" : "China"

		},
		{
			"name": "Milan",
			"latitude": 45.4668,
			"longitude": 9.1905,
"country" : "Italy"

		},
		{
			"name": "Aleppo",
			"latitude": 36.19924,
			"longitude": 37.1637253,
"country" : "Syria"

		},
		{
			"name": "Saint Petersburg",
			"latitude": 59.938732,
			"longitude": 30.316229,
"country" : "Russia"

		},
		{
			"name": "Dar es Salaam",
			"latitude": -6.8160837,
			"longitude": 39.2803583,
"country" : "Tanzania"

		},
		{
			"name": "Bandung",
			"latitude": -6.9344694,
			"longitude": 107.6049539,
"country" : "Indonesia"

		},
		{
			"name": "Guadalajara",
			"latitude": 20.6720375,
			"longitude": -103.3383962,
"country" : "Mexico"

		},
		{
			"name": "Barcelona",
			"latitude": 41.3828939,
			"longitude": 2.1774322,
"country" : "Spain"

		},
		{
			"name": "Belo Horizonte",
			"latitude": -19.9227318,
			"longitude": -43.9450948,
"country" : "Brazil"

		},
		{
			"name": "Alexandria",
			"latitude": 31.199004,
			"longitude": 29.894378,
"country" : "Egypt"

		},
		{
			"name": "Kuwait City",
			"latitude": 29.3797091,
			"longitude": 47.9735629,
"country" : "Kuwait"

		},
		{
			"name": "Harbin",
			"latitude": 45.7656666,
			"longitude": 126.6160584,
"country" : "China"

		},
		{
			"name": "Sydney",
			"latitude": -33.8548157,
			"longitude": 151.2164539,
"country" : "Australia"

		},
		{
			"name": "Abidjan",
			"latitude": 5.320357,
			"longitude": -4.016107,
"country" : "Ivory Coast"

		},
		{
			"name": "Casablanca",
			"latitude": 33.5950627,
			"longitude": -7.6187768,
"country" : "Morocco"

		},
		{
			"name": "Melbourne",
			"latitude": -37.8142176,
			"longitude": 144.9631608,
"country" : "Australia"

		},
		{
			"name": "Phoenix",
			"latitude": 33.4484367,
			"longitude": -112.0741417,
"country" : "USA"

		},
		{
			"name": "Colombo",
			"latitude": 6.9349969,
			"longitude": 79.8538463,
"country" : "Sri Lanka"

		},
		{
			"name": "Monterrey",
			"latitude": 25.6802019,
			"longitude": -100.3152586,
"country" : "Mexico"

		},
		{
			"name": "Surabaya",
			"latitude": -7.2459717,
			"longitude": 112.7378266,
"country" : "Indonesia"

		},
		{
			"name": "Ankara",
			"latitude": 39.9207774,
			"longitude": 32.854067,
"country" : "Turkey"

		}
	]
        """.trimIndent()