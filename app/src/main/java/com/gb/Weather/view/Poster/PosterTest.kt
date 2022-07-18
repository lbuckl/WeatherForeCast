package com.gb.Weather.view.Poster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gb.Weather.databinding.FragmentWeatherPosterBinding
import com.gb.Weather.shared.showSnackBarErrorMsg
import com.gb.Weather.viewmodel.AppState
import com.gb.Weather.viewmodel.PosterInfoViewModel

class PosterTest: Fragment() {
    companion object {
        lateinit var viewModel_poster: PosterInfoViewModel
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
        viewModel_poster = ViewModelProvider(this).get(PosterInfoViewModel::class.java)
        viewModel_poster.getLiveData().observe(viewLifecycleOwner
        ) { t -> renderData(t) }

    }

    private fun renderData(appState: AppState) {
        Log.d("@@@","SuccesPoster")
        when (appState){
            is AppState.Success -> {
                with(binding){
                    cityName.text = appState.weather.city.name
                    temperatureValue.text = appState.weather.temperature.toString()
                    feelsLikeValue.text = appState.weather.feelsLike.toString()
                    cityCoordinates.text = "${appState.weather.city.lat}/${appState.weather.city.lon}"
                }
            }
            is AppState.Error -> {
                view?.showSnackBarErrorMsg(appState.error.toString())
            }
            is AppState.Loading ->{
                with(binding){
                    cityName.text = appState.weather.city.name
                    cityCoordinates.text = "${appState.weather.city.lat}/${appState.weather.city.lon}"
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

