package com.gb.Weather.model
import com.gb.Weather.model.dto.WeatherDTO

class RepositoryRemoteImpl:RepositorySingleCity {
    override fun getWeather(weatherDTO: WeatherDTO): WeatherDTO {
        return weatherDTO
    }
}