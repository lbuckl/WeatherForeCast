package com.gb.Weather.view.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gb.Weather.databinding.FragmentWeatherListBinding

import com.gb.Weather.viewmodel.AppState
import com.gb.Weather.viewmodel.WeatherListViewModel
import com.google.android.material.snackbar.Snackbar

class WeatherListFragment : Fragment(){
    companion object {
        fun newInstance() = WeatherListFragment()
    }

//    lateinit var binding: FragmentWeatherPosterBinding
    lateinit var binding_list: FragmentWeatherListBinding
    lateinit var viewModel: WeatherListViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //Биндинг для прямой связи View
        //binding = FragmentWeatherListBinding.inflate(inflater,requireParentFragment(),false)
        binding_list = FragmentWeatherListBinding.inflate(inflater)
        return binding_list.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Получаем ссылку на наш экземпляр ViewModel
        viewModel = ViewModelProvider(this).get(WeatherListViewModel::class.java)
        //получаем данные из LiveData и
        //запускаем подписку на AppState
        viewModel.getLiveData().observe(viewLifecycleOwner,object : Observer<AppState>{
            override fun onChanged(t: AppState) {
                renderData(t)
            }
        })
        viewModel.getWeatherListForFavorite()

        binding_list.buttonFavorite.setOnClickListener{
            viewModel.getWeatherListForFavorite()
        }

        binding_list.buttonRus.setOnClickListener{
            viewModel.getWeatherListForRussia()
        }

        binding_list.buttonWorld.setOnClickListener{
            viewModel.getWeatherListForWorld()
        }
        //viewModel.sentRequest()
    }

    //Подписка на изменение AppState и выполнение операций по триггеру
    private fun renderData(appState: AppState){
        when (appState){
            is AppState.LoadCities -> {
                binding_list.weatherRecyclerview.adapter = WeatherListRecyclerAdapter(appState.weatherList)
            }
            is AppState.Error -> {/*TODO HW*/
                val result = appState.error.message;
                val toast = Toast.makeText(context, result, Toast.LENGTH_LONG)
                toast.show()
            }

            is AppState.Loading -> {/*TODO HW*/
                Snackbar.make(binding_list.root, "Loading", Snackbar.LENGTH_LONG).show()
            }

            is AppState.Success -> {
                /*val toast = Toast.makeText(context, "Успешно загружено", Toast.LENGTH_LONG)
                toast.show()
                val result = appState.weatherData
                binding.cityName.text = result.city.name
                binding.temperatureValue.text = result.temperature.toString()
                binding.feelsLikeValue.text = result.feelsLike.toString()
                binding.cityCoordinates.text = "${result.city.lat}/${result.city.lon}"*/
            }
        }
    }

}
