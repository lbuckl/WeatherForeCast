package com.gb.Weather.view.Poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gb.Weather.databinding.FragmentWeatherPosterBinding
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.dto.Info
import com.gb.Weather.model.dto.WeatherDTO
import com.gb.Weather.view.weatherlist.WeatherListFragment
import com.gb.Weather.viewmodel.AppState
import com.gb.Weather.viewmodel.PosterInfoViewModel
import com.gb.Weather.viewmodel.WeatherListViewModel

class PosterWeatherFragment: Fragment() {
    companion object {
        lateinit var viewModel: PosterInfoViewModel
    }

    lateinit var binding: FragmentWeatherPosterBinding

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
        viewModel = ViewModelProvider(this).get(PosterInfoViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner
        ) { t -> renderData(t) }

    }

    private fun renderData(appState: AppState) {
        when (appState){
            is AppState.Success -> {

                with(binding){
                    //cityName.text = appState.weatherData.fact
                    temperatureValue.text = appState.weatherData.fact.temp.toString()
                    feelsLikeValue.text = appState.weatherData.fact.feelsLike.toString()
                    //cityCoordinates.text = appState.weatherData.info.
                }
            }
        }
    }
}