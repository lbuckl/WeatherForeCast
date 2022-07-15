package com.gb.Weather.view.Poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gb.Weather.databinding.FragmentWeatherPosterBinding
import com.gb.Weather.domain.Weather
import com.gb.Weather.shared.SAVE_WEATHER_KEY

class PosterFragment: Fragment() {

    //region Создание биндинга и удаление при закрытии
    //private var _binding: FragmentWeatherPosterBinding? = null
    //private var binding: FragmentWeatherPosterBinding
    lateinit var binding: FragmentWeatherPosterBinding
        /*get() {
            return _binding!!
        }*/

    override fun onDestroy() {
        super.onDestroy()
        //_binding = null
    }
    //endregion

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
        val weather = (arguments?.getParcelable<Weather>(SAVE_WEATHER_KEY))
        if (weather != null)
        renderData(weather);
    }

    private fun renderData(weather: Weather) {
        with(binding){
        cityName.text = weather.city.name
        temperatureValue.text = weather.temperature.toString()
        feelsLikeValue.text = weather.feelsLike.toString()
        cityCoordinates.text = "${weather.city.lat}/${weather.city.lon}"
        }
    }

    companion object {
        fun newInstance(weather: Weather): PosterFragment {
            val fr = PosterFragment()
            val bundle = Bundle()
            fr.arguments = bundle.apply { putParcelable(SAVE_WEATHER_KEY, weather) }
            return fr
        }
    }

}