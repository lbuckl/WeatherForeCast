package com.gb.Weather.view.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gb.Weather.BuildConfig
import com.gb.Weather.R
import com.gb.Weather.databinding.FragmentWeatherListBinding
import com.gb.Weather.domain.Weather
import com.gb.Weather.shared.showSnackBarError
import com.gb.Weather.view.Poster.OnItemClick
import com.gb.Weather.view.Poster.PosterFragment
import com.gb.Weather.viewmodel.AppState
import com.gb.Weather.viewmodel.WeatherListViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * (Класс был изменен)
 * Сейчас он отвечает за раскрытие списка городов и обработке дейтсвия по клику (открытие постера)
 */
class WeatherListFragment : Fragment(), OnItemClick {

    companion object {
        lateinit var viewModel: WeatherListViewModel
        fun newInstance() = WeatherListFragment()
    }
    lateinit var binding_list: FragmentWeatherListBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        //Биндинг для прямой связи View
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

    }

    //Подписка на изменение AppState и выполнение операций по триггеру
    private fun renderData(appState: AppState){
        when (appState){
            is AppState.LoadCities -> {
                binding_list.weatherRecyclerview.adapter = WeatherListRecyclerAdapter(appState.weatherList,this)
            }
            is AppState.Error -> {/*TODO HW*/
                view?.showSnackBarError()
            }

            is AppState.Loading -> {/*TODO HW*/
                Snackbar.make(binding_list.root, "Loading", Snackbar.LENGTH_LONG).show()
            }

            is AppState.Success -> {
            }
        }
    }

    override fun onItemClick(weather: Weather) {
        requireActivity().supportFragmentManager.beginTransaction().hide(this).add(
            R.id.container, PosterFragment.newInstance(weather)
        ).addToBackStack("").commit()
    }
}
