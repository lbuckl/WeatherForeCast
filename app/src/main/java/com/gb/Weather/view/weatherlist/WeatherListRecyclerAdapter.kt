package com.gb.Weather.view.weatherlist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.gb.Weather.MyApp
import com.gb.Weather.R
import com.gb.Weather.databinding.FragmentWeatherListItemBinding
import com.gb.Weather.domain.City
import com.gb.Weather.shared.LAST_LIST

/**
 * Кастомный адаптер для вывода списка городов в recyclerview
 */
class WeatherListRecyclerAdapter (private val weatherListCity:List<City>):
    RecyclerView.Adapter<WeatherListRecyclerAdapter.WeatherViewHolder>() {

    //lateinit var contextParent: Context

    //Создаёт ViewHolder объект опираясь на их количество, но с запасом, чтобы можно было скролить
    //Возвращает наш объект класса ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        //contextParent = parent.context
        val binding = FragmentWeatherListItemBinding.inflate(LayoutInflater.from(parent.context))
        return WeatherViewHolder(binding.root)
    }

    //Связываем используемые текстовые метки с данными
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherListCity[position])

        holder.itemView.setOnClickListener{
            WeatherListFragment.viewModel.loadWeather(weatherListCity[position])
        }

        holder.itemView.setOnLongClickListener {
            val popupMenu = PopupMenu(MyApp.getMyApp(),it)
            popupMenu.menuInflater.inflate(R.menu.popup_menu_city,popupMenu.menu)
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener {item ->
                when (item.itemId){
                    (R.id.popup_position_delete) -> {
                        Log.d("@@@","Delete")
                        WeatherListFragment.viewModel.deleteFavoriteCity(weatherListCity[position])}
                }
                true
            }
            true
        }
    }

    //Возвращает количество элементов списка
    override fun getItemCount(): Int {
        return weatherListCity.size
    }

    //Вложенный класс для отображения данных в fragment_weather_list_item.xml
    inner class WeatherViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(city: City){
            val binding = FragmentWeatherListItemBinding.bind(itemView)
            binding.cityItem.text = city.name
        }
    }
}
