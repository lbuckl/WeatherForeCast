package com.gb.k_2135_2136_2.viewmodel

import com.gb.k_2135_2136_2.domain.Weather
import java.lang.Exception

/**
 * Класс описывающий состояния
 * Состояния меняются из WeatherListViewModel функцией sentRequest
 * Состояния читаются из WeatherListFragment функцией renderData
 */
sealed class AppState {
    data class Success(val weatherData: Weather) : AppState()
    data class Error(val error: Exception) : AppState() // Изменил Throwable на Exeption
    object Loading : AppState()
}
