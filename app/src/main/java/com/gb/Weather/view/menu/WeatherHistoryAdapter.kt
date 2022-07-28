package com.gb.Weather.view.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.Weather.databinding.FragmentWeatherListItemBinding
import com.gb.Weather.domain.City
import com.gb.Weather.domain.Weather
import com.gb.Weather.view.weatherlist.WeatherListFragment

/**
 * Кастомный адаптер для вывода списка погоды из истории
 */
class WeatherHistoryAdapter(private val weatherList: List<Weather>):
    RecyclerView.Adapter<WeatherHistoryAdapter.WeatherViewHolder>(){

        //Создаёт ViewHolder объект опираясь на их количество, но с запасом, чтобы можно было скролить
        //Возвращает наш объект класса ViewHolder
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
            val binding = FragmentWeatherListItemBinding.inflate(LayoutInflater.from(parent.context))
            return WeatherViewHolder(binding.root)
        }

        //Связываем используемые текстовые метки с данными
        override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
            holder.bind(weatherList[position])

            /*holder.itemView.setOnClickListener{
                WeatherListFragment.viewModel.loadWeather(weatherList[position])
            }*/
        }

        //Возвращает количество элементов списка
        override fun getItemCount(): Int {
            return weatherList.size
        }

        //Вложенный класс для отображения данных в fragment_weather_list_item.xml
        inner class WeatherViewHolder(view: View): RecyclerView.ViewHolder(view){
            fun bind(weather: Weather){
                val binding = FragmentWeatherListItemBinding.bind(itemView)
                binding.cityItem.text = weather.city.name
                binding.textTemperature.text = weather.temperature.toString() + "℃"
            }
        }
    }