package com.gb.Weather.model
import com.gb.Weather.shared.WeatherLoader

class RepositoryRemoteImpl:RepositorySingleCity {
    override fun getWeather(lat: Double, lon: Double) {
        WeatherLoader.requestWeatherTDO(lat,lon)
    }
}