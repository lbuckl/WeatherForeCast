package com.gb.Weather.viewmodel

import com.gb.Weather.domain.City
import com.gb.Weather.domain.Weather

/**
 * Класс описывающий состояния
 * Состояния меняются из WeatherListViewModel функцией sentRequest и openPoster
 * Состояния читаются из WeatherListFragment функцией renderData
 */
sealed class WeatherListAppState {
    data class LoadCities(val weatherList: List<City>) : WeatherListAppState()// Отображение списка городов
    data class Loading(val city: City) : WeatherListAppState() // загрузка
}
