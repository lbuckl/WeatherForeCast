package com.gb.Weather.model

import com.gb.Weather.model.dto.WeatherDTO

class RepositoryRemoteImpl:RepositorySingleCity {
    override fun getWeather(lat: Double, lon: Double): WeatherDTO {

        //Выполнить запрос вернуть WeatherDTO

        return weatheDTO
    }
}