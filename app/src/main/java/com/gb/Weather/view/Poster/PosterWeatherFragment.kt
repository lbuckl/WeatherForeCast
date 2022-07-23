package com.gb.Weather.view.Poster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gb.Weather.R
import com.gb.Weather.databinding.FragmentWeatherPosterBinding
import com.gb.Weather.shared.showSnackBarErrorMsg
import com.gb.Weather.view.LoadingFragment
import com.gb.Weather.viewmodel.PosterInfoAppState
import com.gb.Weather.viewmodel.PosterInfoViewModel

class PosterWeatherFragment: Fragment() {
    companion object {
        lateinit var viewModel_poster: PosterInfoViewModel
    }

    lateinit var binding: FragmentWeatherPosterBinding
    val loadingFragment = LoadingFragment()

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
        Log.d("@@@","onViewCreated")
        viewModel_poster = ViewModelProvider(this).get(PosterInfoViewModel::class.java)
        viewModel_poster.getLiveData().observe(viewLifecycleOwner) { t -> renderData(t) }
        viewModel_poster.init()
    }

    private fun renderData(posterInfoAppState: PosterInfoAppState) {
        Log.d("@@@","renderData")
        when (posterInfoAppState){
            is PosterInfoAppState.Success -> {
                Log.d("@@@","SuccesPoster")
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .remove(loadingFragment)
                    .commit()

                with(binding){
                    cityName.text = posterInfoAppState.weather.city.name
                    temperatureValue.text = posterInfoAppState.weather.temperature.toString()
                    feelsLikeValue.text = posterInfoAppState.weather.feelsLike.toString()
                    cityCoordinates.text = "${posterInfoAppState.weather.city.lat}/${posterInfoAppState.weather.city.lon}"
                }
            }
            is PosterInfoAppState.Error -> {
                Log.d("@@@","ErrorPoster")
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .remove(loadingFragment)
                    .commit()
                view?.showSnackBarErrorMsg(posterInfoAppState.error.toString())
            }
            is PosterInfoAppState.Loading ->{
                Log.d("@@@","LoadingPoster")
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container,loadingFragment)
                    .commit()
                with(binding){
                    cityName.text = posterInfoAppState.weather.city.name
                    cityCoordinates.text = "${posterInfoAppState.weather.city.lat}/${posterInfoAppState.weather.city.lon}"
                }
            }
            else -> {
                Log.d("@@@","getWeather")
                viewModel_poster.getWeather()
            }
        }
    }
}