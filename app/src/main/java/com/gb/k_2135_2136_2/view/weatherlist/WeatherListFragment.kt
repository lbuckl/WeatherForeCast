package com.gb.k_2135_2136_2.view.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gb.k_2135_2136_2.databinding.FragmentWeatherListBinding
import com.gb.k_2135_2136_2.viewmodel.AppState
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.Semaphore

class WeatherListFragment : Fragment() {


    companion object {
        fun newInstance() = WeatherListFragment()
    }
    lateinit var v: View
    lateinit var binding: FragmentWeatherListBinding
    lateinit var viewModel: WeatherListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWeatherListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        v = view
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherListViewModel::class.java)
        //запускаем подписку на AppState
        viewModel.getLiveData().observe(viewLifecycleOwner,object : Observer<AppState>{
            override fun onChanged(t: AppState) {
                renderData(t)
            }
        })
        viewModel.sentRequest()
    }


    private fun renderData(appState: AppState){
        when (appState){
            is AppState.Error -> {/*TODO HW*/
                val result = appState.error.message;
                val toast = Toast.makeText(context, result, Toast.LENGTH_LONG)
                toast.show()
            }

            is AppState.Loading -> {/*TODO HW*/
                Snackbar.make(v, "Loading", Snackbar.LENGTH_LONG).show()
            }

            is AppState.Success -> {

                val toast = Toast.makeText(context, "Успешно загружено", Toast.LENGTH_LONG)
                toast.show()
                val result = appState.weatherData
                binding.cityName.text = result.city.name
                binding.temperatureValue.text = result.temperature.toString()
                binding.feelsLikeValue.text = result.feelsLike.toString()
                binding.cityCoordinates.text = "${result.city.lat}/${result.city.lon}"
            }
        }
    }

}
