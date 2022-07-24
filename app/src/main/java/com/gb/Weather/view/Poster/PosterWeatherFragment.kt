package com.gb.Weather.view.Poster

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.gb.Weather.R
import com.gb.Weather.databinding.FragmentWeatherPosterBinding
import com.gb.Weather.shared.showSnackBarErrorMsg
import com.gb.Weather.view.LoadingFragment
import com.gb.Weather.viewmodel.PosterInfoAppState
import com.gb.Weather.viewmodel.PosterInfoViewModel
import com.squareup.picasso.Picasso

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
        viewModel_poster = ViewModelProvider(this).get(PosterInfoViewModel::class.java)
        viewModel_poster.getLiveData().observe(viewLifecycleOwner) { t -> renderData(t) }
        viewModel_poster.init()
        try {
            Picasso.get()
                .load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
                .into(binding.headerIcon)
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    private fun renderData(posterInfoAppState: PosterInfoAppState) {
        when (posterInfoAppState){
            is PosterInfoAppState.Success -> {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .remove(loadingFragment)
                    .commit()

                with(binding){
                    cityName.text = posterInfoAppState.weather.city.name
                    temperatureValue.text = posterInfoAppState.weather.temperature.toString()
                    feelsLikeValue.text = posterInfoAppState.weather.feelsLike.toString()
                    cityCoordinates.text = "${posterInfoAppState.weather.city.lat}/${posterInfoAppState.weather.city.lon}"

                    weatherIcon.loadWeatherIcon(
                        "https://yastatic.net/weather/i/icons/funky/dark/${posterInfoAppState.weather.icon}.svg")
                    }
            }
            is PosterInfoAppState.Error -> {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .remove(loadingFragment)
                    .commit()
                view?.showSnackBarErrorMsg(posterInfoAppState.error.toString())
            }
            is PosterInfoAppState.Loading ->{
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
                viewModel_poster.getWeather()
            }
        }
    }

    private fun ImageView.loadWeatherIcon(url: String) {

        val imageLoader = context?.let {
            ImageLoader.Builder(it)
                .components {
                    add(SvgDecoder.Factory())
                }
                .build()
        }

        val request = context?.let {
            ImageRequest.Builder(it)
                .data(url)
                .crossfade(true)
                .target(this)
                .build()
        }
        imageLoader?.enqueue(request!!)
    }
}