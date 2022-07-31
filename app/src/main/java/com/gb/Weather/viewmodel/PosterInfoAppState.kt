package com.gb.Weather.viewmodel

import com.gb.Weather.domain.City
import com.gb.Weather.domain.Weather

/**
 * Класс описывающий состояния
 * Состояния меняются из WeatherListViewModel функцией sentRequest и openPoster
 * Состояния читаются из WeatherListFragment функцией renderData
 */
sealed class PosterInfoAppState {
    data class Loading(val city: City) : PosterInfoAppState() // загрузка
    data class Success(val weather: Weather) : PosterInfoAppState() //Действие при удачной загрузке
    data class Error(val error: Exception) : PosterInfoAppState() // Действие при ошибке
    object Empty: PosterInfoAppState()
}
