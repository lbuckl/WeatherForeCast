package com.gb.Weather.view.Poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.gb.Weather.R
import com.gb.Weather.databinding.FragmentWeatherPosterBinding
import com.gb.Weather.domain.Weather
import com.gb.Weather.shared.showSnackBarErrorMsg
import com.gb.Weather.view.LoadingFragment
import com.gb.Weather.viewmodel.PosterInfoAppState
import com.gb.Weather.viewmodel.PosterInfoViewModel
import com.squareup.picasso.Picasso

class PosterWeatherFragment: Fragment() {
    companion object {
        lateinit var viewModel_poster: PosterInfoViewModel
    }

    private val loadingFragment = LoadingFragment()
    private var _binding: FragmentWeatherPosterBinding? = null
    private val binding: FragmentWeatherPosterBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherPosterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel_poster = ViewModelProvider(this).get(PosterInfoViewModel::class.java)
        viewModel_poster.getLiveData().observe(viewLifecycleOwner) { t -> renderData(t) }
        viewModel_poster.init()
        binding.headerIcon?.let{
            try {
                Picasso.get()
                    .load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
                    .into(it)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        binding.imageViewSetFavorite!!.setOnClickListener{
            viewModel_poster.setFavoriteCity()
            val ts = binding.imageViewSetFavorite!!.setImageResource(android.R.drawable.btn_star_big_on)
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
                    cityCoordinates?.text = "${posterInfoAppState.weather.city.lat}/${posterInfoAppState.weather.city.lon}"

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
                    cityName.text = posterInfoAppState.city.name
                    cityCoordinates?.let {
                        it.text = "${posterInfoAppState.city.lat}/${posterInfoAppState.city.lon}"
                    }
                }
            }
            else -> {
                viewModel_poster.getWeather()
            }
        }
    }

    //Функция для загрузки иконки погоды через COIL
    private fun ImageView.loadWeatherIcon(url: String) {

        //инцииализирем загрузчик
        val imageLoader = context?.let {
            ImageLoader.Builder(it)
                .components {
                    add(SvgDecoder.Factory())
                }
                .build()
        }
        //формируем запрос
        val request = context?.let {
            ImageRequest.Builder(it)
                .data(url)
                .crossfade(true)
                .target(this)
                .build()
        }
        //выполняем запрос через загрузчик
        imageLoader?.enqueue(request!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        requireActivity().supportFragmentManager
            .beginTransaction()
            .remove(loadingFragment)
            .commit()
    }
}