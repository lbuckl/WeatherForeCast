package com.gb.Weather.model

import com.gb.Weather.MyApp
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.WeatherRequestHistory
import com.gb.Weather.shared.entityListToWeatherList
import com.gb.Weather.shared.entityToWeather
import com.gb.Weather.shared.weatherToEntity

class RepositoryRequestHistory:WeatherRequestHistory {
    override fun getHistoryList(): List<Weather> {
        return entityListToWeatherList(MyApp.getWeatherDatabase().weatherDao().getEntityList())
    }

    override fun addWeatherToHistory(weather: Weather) {
        MyApp.getWeatherDatabase().weatherDao().insert(weatherToEntity(weather))
    }
}