package com.gb.Weather.viewmodel

import com.gb.Weather.domain.Weather

/**
 * Класс описывающий состояния
 * Состояния меняются из WeatherListViewModel функцией sentRequest и openPoster
 * Состояния читаются из WeatherListFragment функцией renderData
 */
sealed class WeatherListAppState {
    data class LoadCities(val weatherList: List<Weather>) : WeatherListAppState()// Отображение списка городов
    data class Loading(val weather: Weather) : WeatherListAppState() // загрузка
}
