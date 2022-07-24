package com.gb.Weather.model
import com.gb.Weather.domain.Weather
import com.gb.Weather.domain.getDefaultCity

object RepositoryRemoteImpl:RepositorySingleCity {
    private var data = Weather(getDefaultCity(),0,0)

    override fun setWeather(weather: Weather) {
        data = weather
    }

    override fun getWeather(): Weather{
        return data
    }

}