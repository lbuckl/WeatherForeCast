package com.gb.Weather.view.Poster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gb.Weather.databinding.FragmentWeatherPosterBinding
import com.gb.Weather.view.weatherlist.WeatherListFragment
import com.gb.Weather.viewmodel.AppState

class PosterWeatherFragment: Fragment() {
    companion object {
        //lateinit var viewModel: PosterInfoViewModel
    }

    lateinit var binding: FragmentWeatherPosterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherPosterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProvider(this).get(PosterInfoViewModel::class.java)
        WeatherListFragment.viewModel.getLiveData().observe(viewLifecycleOwner
        ) { t -> renderData(t) }
    }

    private fun renderData(appState: AppState) {
        Log.d("@@@","SuccesPoster")
        when (appState){
            is AppState.Success -> {
                with(binding){
                    cityName.text = appState.weather.city.name
                    temperatureValue.text = appState.weatherData.fact.temp.toString()
                    feelsLikeValue.text = appState.weatherData.fact.feelsLike.toString()
                    cityCoordinates.text = "${appState.weatherData.info.lat}/${appState.weatherData.info.lon}"
                }
            }
            else -> {
                with(binding){
                cityName.text = "Ошибка"
                temperatureValue.text = "Ошибка"
                feelsLikeValue.text = "Ошибка"
                cityCoordinates.text = "Ошибка/Ошибка"
                }
            }
        }
    }
}

