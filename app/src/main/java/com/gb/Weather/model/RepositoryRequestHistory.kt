package com.gb.Weather.model

import com.gb.Weather.domain.Weather
import com.gb.Weather.model.WeatherRequestHistory

class RepositoryRequestHistory:WeatherRequestHistory {
    override fun getHistoryList(): List<Weather> {
        TODO("Not yet implemented")
    }

    override fun addWeatherToHistory(weather: Weather) {
        TODO("Not yet implemented")
    }
}