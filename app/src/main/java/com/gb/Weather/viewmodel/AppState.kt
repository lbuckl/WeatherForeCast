package com.gb.Weather.viewmodel

import com.gb.Weather.domain.Weather

/**
 * Класс описывающий состояния
 * Состояния меняются из WeatherListViewModel функцией sentRequest и openPoster
 * Состояния читаются из WeatherListFragment функцией renderData
 */
sealed class AppState {
    data class LoadCities(val weatherList: List<Weather>) : AppState()// Отображение списка городов
    data class Loading(val weather: Weather) : AppState() // загрузка
    data class Success(val weather: Weather) : AppState() //Действие при удачной загрузке
    data class Error(val error: Exception) : AppState() // Действие при ошибке
}
