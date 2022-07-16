package com.gb.Weather.viewmodel

import com.gb.Weather.domain.Weather
import com.gb.Weather.model.dto.WeatherDTO

/**
 * Класс описывающий состояния
 * Состояния меняются из WeatherListViewModel функцией sentRequest и openPoster
 * Состояния читаются из WeatherListFragment функцией renderData
 */
sealed class AppState {
    data class Loading(val weather: Weather) : AppState() // загрузка
    data class LoadCities(val weatherList: List<Weather>) : AppState()// Отображение списка городов
    data class Success(val weather: Weather,val weatherData: WeatherDTO) : AppState() //Действие при удачной загрузке
    data class Error(val error: Exception) : AppState() // Действие при ошибке
}
