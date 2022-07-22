package com.gb.Weather.view.weatherlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.Weather.databinding.FragmentWeatherListItemBinding
import com.gb.Weather.domain.Weather

/**
 * Кастомный адаптер для вывода списка городов в recyclerview
 */
class WeatherListRecyclerAdapter (private val weatherListCity:List<Weather>):
    RecyclerView.Adapter<WeatherListRecyclerAdapter.WeatherViewHolder>() {

    //Создаёт ViewHolder объект опираясь на их количество, но с запасом, чтобы можно было скролить
    //Возвращает наш объект класса ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = FragmentWeatherListItemBinding.inflate(LayoutInflater.from(parent.context))
        return WeatherViewHolder(binding.root)
    }

    //Связываем используемые текстовые метки с данными
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherListCity[position])
        holder.itemView.setOnClickListener{
            //callback.onItemClick(weatherListCity[position])
            WeatherListFragment.viewModel.loadWeather(weatherListCity[position])
        }
    }

    //Возвращает количество элементов списка
    override fun getItemCount(): Int {
        return weatherListCity.size
    }

    //Вложенный класс для отображения данных в fragment_weather_list_item.xml
    inner class WeatherViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(weather: Weather){
            val binding = FragmentWeatherListItemBinding.bind(itemView)
            binding.cityItem.text = weather.city.name
        }
    }
}