package com.gb.k_2135_2136_2.viewmodel

import com.gb.k_2135_2136_2.domain.Weather
import java.lang.Exception

/**
 * Класс описывающий состояния
 * Состояния меняются из WeatherListViewModel функцией sentRequest
 * Состояния читаются из WeatherListFragment функцией renderData
 */
sealed class AppState {
    object Loading : AppState() // загрузка
    data class LoadCities(val weatherList: List<Weather>) : AppState()// Отображение списка городов
    data class Success(val weatherData: Weather) : AppState() //Действие при удачной загрузке
    data class Error(val error: Exception) : AppState() // Действие при ошибке

}
