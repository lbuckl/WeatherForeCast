package com.gb.Weather.view.Poster

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gb.Weather.databinding.FragmentWeatherPosterBinding
import com.gb.Weather.domain.Weather
import com.gb.Weather.services.WeatherLoaderService
import com.gb.Weather.shared.SAVE_WEATHER_KEY

class PosterFragment: Fragment() {

    lateinit var binding: FragmentWeatherPosterBinding

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherPosterBinding.inflate(inflater)
        //_binding = FragmentWeatherPosterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    /*private fun renderData(weather: Weather) {
        with(binding){
        cityName.text = weather.city.name
        temperatureValue.text = weather.temperature.toString()
        feelsLikeValue.text = weather.feelsLike.toString()
        cityCoordinates.text = "${weather.city.lat}/${weather.city.lon}"
        }
    }*/

    /*companion object {
        fun newInstance(weather: Weather): PosterFragment {
            val fr = PosterFragment()
            val bundle = Bundle()
            fr.arguments = bundle.apply { putParcelable(SAVE_WEATHER_KEY, weather) }
            return fr
        }
    }*/

}