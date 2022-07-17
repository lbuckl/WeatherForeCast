package com.gb.Weather.model
import com.gb.Weather.domain.Weather

class RepositoryRemoteImpl:RepositorySingleCity {
    override fun getWeather(weather: Weather): Weather{
        return weather
    }
}