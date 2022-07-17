package com.gb.Weather.view.weatherlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gb.Weather.R
import com.gb.Weather.databinding.FragmentWeatherListBinding
import com.gb.Weather.domain.Weather
import com.gb.Weather.shared.WeatherLoader
import com.gb.Weather.shared.showSnackBarErrorMsg
import com.gb.Weather.view.LoadingFragment
import com.gb.Weather.view.Poster.OnItemClick
import com.gb.Weather.view.Poster.PosterFragment
import com.gb.Weather.view.Poster.PosterWeatherFragment
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

    private lateinit var binding_list: FragmentWeatherListBinding

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
        viewModel.getLiveData().observe(viewLifecycleOwner
        ) { t -> renderData(t) }

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
        val loadingFragment = LoadingFragment()
        when (appState){
            is AppState.LoadCities -> {
                Log.d("@@@","LoadCities")
                binding_list.weatherRecyclerview.adapter = WeatherListRecyclerAdapter(appState.weatherList,this)
            }

            is AppState.Loading -> {
                Log.d("@@@","Loading")
                Snackbar.make(binding_list.root, "Loading", Snackbar.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager
                    .beginTransaction().hide(this)
                    .add(R.id.container, loadingFragment)
                    .addToBackStack("List")
                    .commit()
                //WeatherLoader.requestWeatherTDO(appState.weather)
            }

            is AppState.Success -> {
                Log.d("@@@","Succes")
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .remove(loadingFragment)
                    .replace(R.id.container, PosterWeatherFragment())
                    .addToBackStack("")
                    .commit()
            }

            is AppState.Error -> {
                Log.d("@@@","Error")
                view?.showSnackBarErrorMsg(appState.error.toString())
                requireActivity().supportFragmentManager
                        .beginTransaction()
                        .remove(loadingFragment)
                        .replace(R.id.container, this)
                        .commit()
            }
        }
    }

    override fun onItemClick(weather: Weather) {
        requireActivity().supportFragmentManager.beginTransaction().hide(this).add(
            R.id.container, PosterFragment.newInstance(weather)
        ).addToBackStack("").commit()
    }
}
