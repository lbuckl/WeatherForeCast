package com.gb.Weather.view.weatherlist

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gb.Weather.R
import com.gb.Weather.databinding.FragmentWeatherListBinding
import com.gb.Weather.databinding.FragmentWeatherPosterBinding
import com.gb.Weather.shared.showSnackBarErrorMsg
import com.gb.Weather.shared.showSnackBarInfoMsg
import com.gb.Weather.view.Poster.PosterWeatherFragment
import com.gb.Weather.viewmodel.WeatherListAppState
import com.gb.Weather.viewmodel.WeatherListViewModel

/**
 * (Класс был изменен)
 * Сейчас он отвечает за раскрытие списка городов и обработке дейтсвия по клику (открытие постера)
 */
class WeatherListFragment : Fragment() {

    companion object {
        lateinit var viewModel: WeatherListViewModel
        fun newInstance() = WeatherListFragment()
    }

    var isConnection = true
    //private lateinit var binding_list: FragmentWeatherListBinding
    private var _binding_list: FragmentWeatherListBinding? = null
    private val binding_list: FragmentWeatherListBinding
        get() {
            return _binding_list!!
        }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        //Биндинг для прямой связи View
        _binding_list = FragmentWeatherListBinding.inflate(inflater)
        //@@@
        //регистрация ресивера для контроля сети
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context?.registerReceiver(networkStateReceiver, filter)

        return binding_list.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Получаем ссылку на наш экземпляр ViewModel
        viewModel = ViewModelProvider(this).get(WeatherListViewModel::class.java)
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
    private fun renderData(appState: WeatherListAppState){
        when (appState){
            is WeatherListAppState.LoadCities -> {
                binding_list.weatherRecyclerview.adapter = WeatherListRecyclerAdapter(appState.weatherList)
            }

            is WeatherListAppState.Loading -> {
                if (isConnection){
                    Log.d("@@@","LoadingWLF")
                    requireActivity().supportFragmentManager
                        .beginTransaction().hide(this)
                        .add(R.id.container, PosterWeatherFragment())
                        .addToBackStack("List")
                        .commit()
                    viewModel.refresh()
                }
                else view?.showSnackBarErrorMsg("Please connect to internet")

            }
        }
    }

    //@@@
    //region Ресивер для контроля сети
    private var networkStateReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val noConnectivity =
                intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
            if (!noConnectivity) {
                onConnectionFound()
            } else {
                onConnectionLost()
            }
        }
    }

    fun onConnectionLost() {
        isConnection = false
        view?.showSnackBarErrorMsg("Connection lost")
        //Toast.makeText(context, "Connection lost", Toast.LENGTH_LONG).show()
    }

    fun onConnectionFound() {
        isConnection = true
        //view?.showSnackBarInfoMsg("Connection found")
        //Toast.makeText(context, "Connection found", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding_list = null
        context?.unregisterReceiver(networkStateReceiver)
    }
    //endregion
}
