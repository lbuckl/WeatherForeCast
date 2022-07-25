package com.gb.Weather.examples

import androidx.fragment.app.Fragment

class PosterFragment: Fragment() {

    /*lateinit var binding: FragmentWeatherPosterBinding

    //инициализируем ресивер и определяем действия по обновлению данных
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("@@@", "onReceive ${binding.root}")
            intent?.let {
                it.getParcelableExtra<Weather>(BUNDLE_WEATHER_KEY)
                    ?.let { wather ->
                        renderData(wather)
                    }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentWeatherPosterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //регистрируем рессивер
        LocalBroadcastManager.getInstance(requireContext())
            .registerReceiver(receiver, IntentFilter(WEATHER_LOADED_WAVE))
    }

    private fun renderData(weather: Weather) {
        with(binding){
        cityName.text = weather.city.name
        temperatureValue.text = weather.temperature.toString()
        feelsLikeValue.text = weather.feelsLike.toString()
        cityCoordinates.text = "${weather.city.lat}/${weather.city.lon}"
        }
    }*/
}