package com.gb.k_2135_2136_2.viewmodel

import com.gb.k_2135_2136_2.domain.Weather
import com.gb.k_2135_2136_2.model.Repository
import java.lang.Exception

sealed class AppState {
    data class Success(val weatherData: Weather) : AppState()
    data class Error(val error: Exception) : AppState() // Изменил Throwable на Exeption
    object Loading : AppState()
}
