package com.gb.Weather.viewmodel

import com.gb.Weather.domain.City
import com.gb.Weather.domain.Weather

sealed class HistoryAppState {
    data class LoadedHistory(val weatherList: List<Weather>) : HistoryAppState()// Отображение списка городов
    object Loading : WeatherListAppState() // загрузка
}